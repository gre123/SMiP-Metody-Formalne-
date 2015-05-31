package smip.rechabilityGraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import model.PetriGraph;
import model.Place;
import model.ReachabilityArc;
import model.ReachabilityGraph;
import model.ReachabilityVertex;
import model.Transition;

/**
 * @author Tomek
 */
public class ReachabilityGraphCalculator {

    private final ReachabilityGraph reachabilityGraph;

    public ReachabilityGraphCalculator(ReachabilityGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    private void calculateReachabilityGraph(List<ReachabilityVertex> vertexList, PetriGraph graph, Transition[] transitions, Place[] places) {
        int[][] incidenceMatrix = graph.getNincidence();
        while (!vertexList.isEmpty()) {
            ReachabilityVertex parentVertex = vertexList.remove(0);

            Set<Transition> activeTransitions = parentVertex.getActiveTransitions();
            for (int i = 0; i < transitions.length; i++) {
                if (activeTransitions.contains(transitions[i])) {
                    int[] newMarkers = new int[places.length];
                    for (int j = 0; j < places.length; j++) {
                        places[j].setResources(parentVertex.getMarker(j) + incidenceMatrix[j][i]);
                        newMarkers[j] = places[j].getResources();
                    }
                    ReachabilityVertex newVertex = new ReachabilityVertex(newMarkers);
                    if (!reachabilityGraph.containsVertex(newVertex)) {
                        graph.updateGraphTransitionStates();
                        for (Transition transition : transitions) {
                            if (transition.getActive()) {
                                newVertex.addActiveTransition(transition);
                            }
                        }
                        reachabilityGraph.addVertex(newVertex);
                        vertexList.add(newVertex);
                    }
                    reachabilityGraph.addEdge(new ReachabilityArc(transitions[i].getId()), parentVertex, newVertex);
                }
            }
        }
        System.out.println(reachabilityGraph.toString());
    }

    public void calculateAll(PetriGraph graph) {
        Place[] places = graph.getPlaceSet().toArray(new Place[graph.getPlaceSet().size()]);
        Arrays.sort(places);
        Transition[] transitions = graph.getTransitionSet().toArray(new Transition[graph.getTransitionSet().size()]);
        Arrays.sort(transitions);

        int[] markers = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            markers[i] = places[i].getResources();
        }

        List<ReachabilityVertex> vertexList = getVertexList(transitions, markers);

        calculateReachabilityGraph(vertexList, graph, transitions, places);

        restoreResources(markers, places);
        graph.updateGraphTransitionStates();

        calculateLiveness(transitions);
        calculateConservation(markers);
        calculateSafeness();
    }

    private List<ReachabilityVertex> getVertexList(Transition[] transitions, int[] markers) {
        List<ReachabilityVertex> vertexList = new LinkedList<>();
        ReachabilityVertex startVertex = new ReachabilityVertex(markers);
        for (Transition transition : transitions) {
            if (transition.getActive()) {
                startVertex.addActiveTransition(transition);
            }
        }
        if (!startVertex.getActiveTransitions().isEmpty()) {
            reachabilityGraph.addVertex(startVertex);
            vertexList.add(startVertex);
            System.out.print("Start vertex: " + startVertex);
        }
        return vertexList;
    }

    private void restoreResources(int[] markers, Place[] places) {
        for (int i = 0; i < places.length; i++) {
            places[i].setResources(markers[i]);
        }
    }

    private boolean calculateLiveness(Transition[] transitions) {
        boolean liveness = true;
        Collection<ReachabilityVertex> vertexes = reachabilityGraph.getVertices();
        Collection<ReachabilityArc> edges = reachabilityGraph.getEdges();

        //sprawdzanie martwych wierzchołków
        for (ReachabilityVertex vertex : vertexes) {
            if (vertex.getActiveTransitions().isEmpty()) {
                liveness = false;
                break;
            }
        }

        //sprawdzanie czy użyte zostały wszystkie przejścia
        if (liveness) {
            List<Integer> transitionsUsed = new LinkedList<>();
            for (ReachabilityArc edge : edges) {
                int transitionId = edge.getTransitionId();
                if (!transitionsUsed.contains(transitionId)) {
                    transitionsUsed.add(transitionId);
                }
            }
            if (transitionsUsed.size() != transitions.length) {
                liveness = false;
            }
        }

        System.out.println("Żywotnośc sieci: " + liveness);
        return liveness;
    }

    private boolean calculateConservation(int[] markers) {
        Collection<ReachabilityVertex> vertexes = reachabilityGraph.getVertices();
        int sumOfInitialMarking = IntStream.of(markers).sum();
        int sumOfVertexMarking;
        boolean conservation = true;
        for (ReachabilityVertex vertex : vertexes) {
            sumOfVertexMarking = IntStream.of(vertex.getMarkers()).sum();
            if (sumOfVertexMarking != sumOfInitialMarking) {
                conservation = false;
                break;
            }
        }

        return conservation;
    }

    private boolean calculateSafeness() {
        int[] vertexMarkers;
        boolean safeness = true;
        Collection<ReachabilityVertex> vertexes = reachabilityGraph.getVertices();
        for (ReachabilityVertex vertex : vertexes) {
            vertexMarkers = vertex.getMarkers();
            for (int vertexMarker : vertexMarkers) {
                if (vertexMarker > 1) {
                    safeness = false;
                    break;
                }
            }
            if (!safeness) {
                break;
            }
        }

        return safeness;
    }

}
