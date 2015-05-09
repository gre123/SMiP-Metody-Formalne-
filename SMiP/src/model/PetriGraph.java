/*
 */
package model;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections15.MultiMap;

/**
 * PetriGraph czyli skierowany graf dwudzielny, kod przerabiany z jakiejś starej
 * implementacji grafu dwudzielnego (BipartiteGraph) z junga 1.7
 *
 * @author Elpidiusz
 */
public class PetriGraph extends DirectedSparseGraph<MyVertex, Arc> {

    private Set<Place> placeSet = new HashSet();
    private Set<Transition> transitionSet = new HashSet();

    public void initialize() {
        placeSet = new HashSet();
        transitionSet = new HashSet();
    }

    public PetriGraph() {
        super();
        placeSet = new HashSet();
        transitionSet = new HashSet();
    }

    /**
     * Returns the set of all vertices from that class. All vertices in the
     * return set will be of class specified in parameter.
     *
     * @param type type of vertex class you want
     */
    public Set getAllVertices(Class type) {
        if (type == Place.class) {
            return Collections.unmodifiableSet(placeSet);
        } else if (type == Transition.class) {
            return Collections.unmodifiableSet(transitionSet);
        } else {
            throw new IllegalArgumentException("Invalid partition specification " + type.getName());
        }
    }

    /**
     * Returns the set of all vertices
     *
     * @param type type of vertex class you want
     */
    public Collection<MyVertex> getAllVertices() {
        return this.getVertices();
    }

    /**
     * Returns the partition for vertex v.
     *
     * @param v
     */
    public Class getPartition(MyVertex v) {
        return v.getClass();
    }

//    /**
//     * Adds a single vertex to the graph in the specified partition.
//     * NIE UŻYWAĆ PÓKI CO,
//     * to będzie konwerter MyVertex do klasy zadanej w drugim parametrze
//     * o ile będzie taka funkcja do czegoś potrzebna.
//     * 
//     * @param v	the vertex to be added to the class
//     * @param choice	the class to which the vertex should be added
//     * @return the input vertex
//     */
//    public MyVertex addVertex(MyVertex v, Choice choice) {
//        String exists = "Specified partition already contains vertex ";
//        String dup = "Another partition already contains vertex ";
//        if (choice == CLASSA) {
//            if (placeSet.contains(v)) {
//                throw new IllegalArgumentException(exists + v);
//            }
//            if (transitionSet.contains(v)) {
//                throw new IllegalArgumentException(dup + v);
//            }
//            placeSet.add(v);
//        } else if (choice == CLASSB) {
//            if (transitionSet.contains(v)) {
//                throw new IllegalArgumentException(exists + v);
//            }
//            if (placeSet.contains(v)) {
//                throw new IllegalArgumentException(dup + v);
//            }
//            transitionSet.add(v);
//        } else {
//            throw new IllegalArgumentException("Invalid partition specification for vertex " + v + ": " + choice);
//        }
//        super.addVertex(v);
//        return v;
//    }
    /**
     * Adds a BipartiteEdge to the Graph. Checks if it is not connecting
     * vertices of the same partition
     *
     * @param bpe a BipartiteEdge
     * @return the edge, now a member of the graph.
     */
    public boolean addBipartiteEdge(Arc arc, MyVertex start, MyVertex end) {
        //trochę naiwne sprawdzanie, gdyby ktoś wsadził więcej typów wierchołków to nie będzie działało
        if (start.getClass() != end.getClass()) {
        }
        return super.addEdge(arc, start, end, EdgeType.DIRECTED);
    }

    /**
     * właściwie do usunięcia, jeśli weryfikacja poprawności łączenia
     * wierzchołków jest na poziomie edytora
     *
     * @param arc
     * @param start
     * @param end
     * @param type
     * @return
     */
    @Override
    public boolean addEdge(Arc arc, MyVertex start, MyVertex end, EdgeType type) {
        if (type == EdgeType.UNDIRECTED) {
            return false;
        }
        return addBipartiteEdge(arc, start, end);
    }

    @Override
    public boolean addVertex(MyVertex vertex) {
        if (vertex.getClass() == Place.class) {
            placeSet.add((Place) vertex);
        } else if (vertex.getClass() == Transition.class) {
            transitionSet.add((Transition) vertex);
        } else {
            throw new IllegalArgumentException("Invalid vertex class in addVertex: " + vertex.getClass().getName());
        }
        return super.addVertex(vertex);
    }

    /**
     * Adds all pairs (key, value) to the multimap from the initial set keySet.
     * Nie wiem czy działa, skopiowane ze wzoru
     *
     * @param set
     * @param hyperEdge
     */
    private static void addAll(MultiMap mm, Set keyset, Object value) {
        for (Iterator iter = keyset.iterator(); iter.hasNext();) {
            Object key = iter.next();
            mm.put(key, value);
        }
    }

    @Override
    public boolean removeVertex(MyVertex v) {
        if (placeSet.contains(v)) {
            placeSet.remove(v);
        } else {
            transitionSet.remove(v);
        }
        return super.removeVertex(v);
    }

    /**
     * Funkcja uaktualniająca stan przejścia (czy jest aktywne, boolean )
     *
     * @param t Przejście do uaktualnienia
     * @return uaktualniony stan przejścia
     */
    public boolean updateTransitionState(Transition t) {
        for (Object place : this.getPredecessors(t)) {
            Arc connectingEdge = this.findEdge((Place) place, t);
            if (((Place) place).getResources() < connectingEdge.getValue()) {
                t.setActive(false);
                return false;
            }
        }
        t.setActive(true);
        return true;
    }

    /**
     * Funkcja uaktualniająca stany przejść całego grafu
     *
     * @return coś jak L4 żywotność
     */
    public boolean updateGraphTransitionStates() {
        boolean alive = true;
        for (Object transition : this.transitionSet) {
            if (!updateTransitionState((Transition) transition)) {
                alive = false;
            }
        }
        return alive;
    }

    /**
     * Coś jak L4 żywotność: wszystkie przejścia są aktywne
     *
     * @return true jesli wszystkie przejscia sa aktywne
     */
    public boolean dummyisGraphAlive() {
        return updateGraphTransitionStates();
    }

    /**
     * mało optymalna implementacja, można by zamiast przeglądać wszystkie miejsca i przejścia
     * skorzystać z np getPredecessors();
     * @return 
     */
    public int[][] getNplus() {
        Object[] placearray = placeSet.toArray();
//        //nie najlepszy sposób na sortowanie, wypadałoby te komparatory ogarnąć w źródłowych klasach
//        Arrays.sort(placearray, new Comparator<Object>() {
//            @Override
//            public int compare(Object p1, Object p2) {
//                return Integer.compare(((Place)p1).id, ((Place)p2).id);
//            }
//        });
        Arrays.sort(placearray);
        Object[] transitionarray = transitionSet.toArray();
//        //nie najlepszy sposób na sortowanie, wypadałoby te komparatory ogarnąć w źródłowych klasach
//        Arrays.sort(transitionarray, new Comparator<Object>() {
//            @Override
//            public int compare(Object p1, Object p2) {
//                return Integer.compare(((Transition)p1).id, ((Transition)p2).id);
//            }
//        });
        Arrays.sort(transitionarray);
        int[][] nplus = new int[placeSet.size()][transitionSet.size()];

        for (int i = 0; i < placearray.length; i++) {
            for (int j = 0; j < transitionarray.length; j++) {
                if (this.isPredecessor((Place) placearray[i], (Transition) transitionarray[j])) {
                    nplus[i][j] = this.findEdge((Transition) transitionarray[j], (Place) placearray[i]).getValue();
                }
            }
        }
        return nplus;
    }
    public int[][] getNminus() {
        Object[] placearray = placeSet.toArray();
        Arrays.sort(placearray);
        Object[] transitionarray = transitionSet.toArray();
        Arrays.sort(transitionarray);
        int[][] nminus = new int[placeSet.size()][transitionSet.size()];

        for (int i = 0; i < placearray.length; i++) {
            for (int j = 0; j < transitionarray.length; j++) {
                if (this.isSuccessor((Place) placearray[i], (Transition) transitionarray[j])) {
                    nminus[i][j] = this.findEdge((Place) placearray[i], (Transition) transitionarray[j]).getValue();
                }
            }
        }
        return nminus;
    }
     public int[][] getNincidence() {
        int[][] nplus = this.getNplus();
        int[][] nminus = this.getNminus();
        int[][] nincidence = new int[nplus.length][nplus[0].length];
        
        for (int i = 0; i < nplus.length; i++) {
            for (int j = 0; j < nplus[0].length; j++) {
                nincidence[i][j] = nplus[i][j] - nminus[i][j];
            }
        }
        return nincidence;
    }

}
