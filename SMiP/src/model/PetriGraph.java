package model;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections15.MultiMap;

/**
 * PetriGraph czyli skierowany graf dwudzielny, kod przerabiany z jakiejś starej
 * implementacji grafu dwudzielnego (BipartiteGraph) z junga 1.7
 *
 * @author Elpidiusz
 */
public class PetriGraph extends DirectedSparseGraph<MyVertex, Arc> implements Serializable {

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
    public Collection<MyVertex> getAllVertices(Class type) {
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
        for (Object place : this.getSuccessors(t)) {
            Arc connectingEdge = this.findEdge(t, (Place) place);
            if (((Place) place).getCapacity() < ((Place) place).getResources() + connectingEdge.getValue()) {
                t.setActive(false);
                return false;
            }
        }
        t.setActive(true);
        return true;
    }

    /**
     * Funkcja uaktualniająca stany przejść całego grafu teraz już faktycznie
     * całego
     *
     * @return coś jak L4 żywotność
     */
    public boolean updateGraphTransitionStates() {
        return howMenyActiveTransition()==transitionSet.size();
    }
    
    public int howMenyActiveTransition(){
        int alive=0;
        for (Object transition : this.transitionSet) {
            if (updateTransitionState((Transition) transition)) {
                alive++;
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
     * mało optymalna implementacja, można by zamiast przeglądać wszystkie
     * miejsca i przejścia skorzystać z np getPredecessors();
     *
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
        if (nplus.length == 0 || nplus[0].length == 0) {
            return null;
        }
        int[][] nincidence = new int[nplus.length][nplus[0].length];

        for (int i = 0; i < nplus.length; i++) {
            for (int j = 0; j < nplus[0].length; j++) {
                nincidence[i][j] = nplus[i][j] - nminus[i][j];
            }
        }
        return nincidence;
    }

    public Map<Place, Integer> getMarking() {
        Map marking = new HashMap<>();
        for (Place place : this.placeSet) {
            marking.put(place, place.resources);
        }
        return marking;
    }

    public void setMarking(Map<Place, Integer> marking) {
        for (Map.Entry<Place, Integer> entry : marking.entrySet()) {
            if (this.containsVertex(entry.getKey())) {
                entry.getKey().setResources(entry.getValue());
            }
        }
    }

    public void setMarkingInf(Map<Place, Integer> marking) {
        for (Map.Entry<Place, Integer> entry : marking.entrySet()) {
            if (this.containsVertex(entry.getKey())) {
                if (entry.getValue() != -1) {
                    entry.getKey().setResources(entry.getValue());
                } else {
                    entry.getKey().setResources(100);
                }
            }
        }
    }

    public List<Transition> getActiveTransitions() {
        this.updateGraphTransitionStates();
        List<Transition> activetransitions = new ArrayList<>();
        for (Transition t : this.transitionSet) {
            if (t.getActive()) {
                activetransitions.add(t);
            }
        }
        return activetransitions;
    }

    public boolean executeTransition(Transition t) {
        if (!this.containsVertex(t)) {
            System.out.println("Nie można wykonać przejścia którego nie ma w grafie!");
            return false;
        }
        if (updateTransitionState(t)) {
            for (Object place : this.getPredecessors(t)) {
                Arc connectingEdge = this.findEdge((Place) place, t);
                ((Place) place).decResources(connectingEdge.getValue());
            }
            for (Object place : this.getSuccessors(t)) {
                Arc connectingEdge = this.findEdge(t, (Place) place);
                ((Place) place).incResources(connectingEdge.getValue());
            }
        }
        //wypadałoby tylko dla powiązanych miejsc, wszędzie gdzie ta funkcja jest użyta
        updateGraphTransitionStates();
        return true;
    }

    public Set<Place> getPlaceSet() {
        return placeSet;
    }

    public Set<Transition> getTransitionSet() {
        return transitionSet;
    }

    /**
     * NIE sprawdziłem czy dla trudniejszych przypadków działa poprawnie, wersja
     * Piotrka z ReachabilitygraphForm jest lepsza, a do tego liczy żywotność
     * itp
     *
     * @return graf osiągalności (if you are lucky)
     */
    public DirectedSparseGraph<Map<Place, Integer>, Transition> getReachabilityGraph() {
        DirectedSparseGraph<Map<Place, Integer>, Transition> rg = new DirectedSparseGraph<>();
        Map<Place, Integer> baseMarking = this.getMarking();
        LinkedList<Map.Entry<Map<Place, Integer>, Transition>> transitionsToCheck = new java.util.LinkedList<>();

        for (Transition t : this.getActiveTransitions()) {
            transitionsToCheck.add(new SimpleEntry<>(baseMarking, t));
        }
        rg.addVertex(baseMarking);
        int count = 0;
        while (!transitionsToCheck.isEmpty() && count <= 300) {
            Map.Entry<Map<Place, Integer>, Transition> entry = transitionsToCheck.poll();
            this.setMarking(entry.getKey());
            this.executeTransition(entry.getValue());
            if (!rg.containsVertex(this.getMarking())) {
                rg.addVertex(this.getMarking());
                for (Transition t : this.getActiveTransitions()) {
                    transitionsToCheck.add(new SimpleEntry<>(this.getMarking(), t));
                }
            }
            //może jakiś check by się tu przydał
            rg.addEdge(new Transition(entry.getValue().id), entry.getKey(), this.getMarking());
            count++;
        }
        this.setMarking(baseMarking);
        return rg;
    }

    /**
     * Chyba dziala dobrze -1 == inf z wyjątkiem ciasnych pętli
     *
     * @return graf pokrycia (if you are very lucky)
     */
    public DirectedSparseGraph<Map<Place, Integer>, Transition> getCoverabilityGraphOld() {
        DirectedSparseGraph<Map<Place, Integer>, Transition> rg = new DirectedSparseGraph<>();
        Map<Place, Integer> baseMarking = this.getMarking();
        LinkedList<Map.Entry<Map<Place, Integer>, Transition>> transitionsToCheck = new java.util.LinkedList<>();

        for (Transition t : this.getActiveTransitions()) {
            transitionsToCheck.add(new SimpleEntry<>(baseMarking, t));
        }
        rg.addVertex(baseMarking);
        int count = 0;
        while (!transitionsToCheck.isEmpty() && count < 300) {
            ArrayList<Place> infPlaces = new ArrayList<>();
            Map.Entry<Map<Place, Integer>, Transition> entry = transitionsToCheck.poll();
            for (Entry<Place, Integer> m : entry.getKey().entrySet()) {
                if (m.getValue().equals(-1)) {
                    infPlaces.add(m.getKey());
                }
            }
            this.setMarkingInf(entry.getKey());
            this.executeTransition(entry.getValue());
            Map<Place, Integer> currentMarking = this.getMarking();

            for (Place p : infPlaces) {
                currentMarking.put(p, -1);
            }
            if (!rg.containsVertex(currentMarking)) {
                for (Map<Place, Integer> znakowanie : rg.getVertices()) {
                    isMore(currentMarking, znakowanie);
                }
                rg.addVertex(currentMarking);
                for (Transition t : this.getActiveTransitions()) {
                    transitionsToCheck.add(new SimpleEntry<>(currentMarking, t));
                }
            }
            rg.addEdge(new Transition(entry.getValue().id), entry.getKey(), currentMarking);
            infPlaces.clear();
            count++;
        }
        this.setMarking(baseMarking);
        return rg;
    }

    /**
     * Powinno działać poprawnie, tak ze dla ciasnych pętli
     *
     * @return graf pokrycia (if you are very lucky)
     */
    public DirectedSparseMultigraph<Map<Place, Integer>, Transition> getCoverabilityGraph() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = new DirectedSparseMultigraph<>();
        Map<Place, Integer> baseMarking = this.getMarking();
        LinkedList<Map.Entry<Map<Place, Integer>, Transition>> transitionsToCheck = new java.util.LinkedList<>();

        for (Transition t : this.getActiveTransitions()) {
            transitionsToCheck.add(new SimpleEntry<>(baseMarking, t));
        }
        cg.addVertex(baseMarking);
        int count = 0;
        while (!transitionsToCheck.isEmpty() && count < 300) {
            ArrayList<Place> infPlaces = new ArrayList<>();
            Map.Entry<Map<Place, Integer>, Transition> entry = transitionsToCheck.poll();
            for (Entry<Place, Integer> m : entry.getKey().entrySet()) {
                if (m.getValue().equals(-1)) {
                    infPlaces.add(m.getKey());
                }
            }
            this.setMarkingInf(entry.getKey());
            this.executeTransition(entry.getValue());
            Map<Place, Integer> currentMarking = this.getMarking();

            for (Place p : infPlaces) {
                currentMarking.put(p, -1);
            }
            if (!cg.containsVertex(currentMarking)) {
                for (Map<Place, Integer> znakowanie : cg.getVertices()) {
                    isMore(currentMarking, znakowanie);
                }
                cg.addVertex(currentMarking);
                for (Transition t : this.getActiveTransitions()) {
                    transitionsToCheck.add(new SimpleEntry<>(currentMarking, t));
                }
            }
            //if (!cg.findEdgeSet(entry.getKey(), currentMarking).contains(entry.getValue())){
            if (!PetriGraph.isTransitionInSetById(cg.findEdgeSet(entry.getKey(), currentMarking), entry.getValue())) {
                cg.addEdge(new Transition(entry.getValue().id), entry.getKey(), currentMarking);
            }
            infPlaces.clear();
            count++;
        }
        this.setMarking(baseMarking);
        return cg;
    }

    /**
     * funkcja do porównywania znakowań przy liczeniu grafu pokrycia ustawia -1
     * na większych miejscach jak trzeba
     *
     * @return true if m1>m2
     */
    public static boolean isMore(Map<Place, Integer> m1, Map<Place, Integer> m2) {
        if (!(m1.keySet().equals(m2.keySet()))) {
            System.out.println("Zbiory miejsc nie są takie same, nie będzie z tego dzieci");
            System.out.println("m1: " + m1.keySet().toString());
            System.out.println("m2: " + m1.keySet().toString());
            return false;
        }
        for (Place p : m1.keySet()) {
            if ((!m1.get(p).equals(-1)) && m1.get(p) < m2.get(p)) {
                return false;
            }
        }
//        Place[] places = m1.keySet().toArray(new Place[m1.keySet().size()]);
//        Arrays.sort(places);
//        for (Place p:places){
//                }
        //skoro jest większe, to tam gdzie jest większe ustawia się inf (-1)
        for (Place p : m1.keySet()) {
            if (m1.get(p) > m2.get(p)) {
                m1.put(p, -1);
            }
        }
        return true;
    }

    public Map<Place, Integer> getPlacesBoundedness() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        Map<Place, Integer> boundaries = new HashMap<>();
        for (Place p : this.placeSet) {
            boundaries.put(p, Integer.MIN_VALUE);
        }
        for (Map<Place, Integer> marking : cg.getVertices()) {
            for (Place p : boundaries.keySet()) {
                if (boundaries.get(p) == -1 || marking.get(p) == -1) {
                    boundaries.put(p, -1);
                } else if (marking.get(p) > boundaries.get(p)) {
                    boundaries.put(p, marking.get(p));
                }
            }
        }
        return boundaries;
    }
    
    public void calculateAndSetPlacesBoundedness() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        Map<Place, Integer> boundaries = new HashMap<>();
        for (Place p : this.placeSet) {
            boundaries.put(p, Integer.MIN_VALUE);
        }
        for (Map<Place, Integer> marking : cg.getVertices()) {
            for (Place p : boundaries.keySet()) {
                if (boundaries.get(p) == -1 || marking.get(p) == -1) {
                    boundaries.put(p, -1);
                } else if (marking.get(p) > boundaries.get(p)) {
                    boundaries.put(p, marking.get(p));
                }
            }
        }
        for (Place p : boundaries.keySet()) {
            p.setBoundary(boundaries.get(p));
        }
    }

    public int getGraphBoundedness() {
        Map<Place, Integer> boundaries = this.getPlacesBoundedness();
        if (boundaries.values().contains(-1)) {
            return -1;
        }
        return Collections.max(boundaries.values());

    }

    public boolean getWeightedGraphConservation(Map<Place, Integer> weights) {
        if (!weights.keySet().equals(this.placeSet)) {
            System.out.println("Mapa wag nie odpowiada miejscom w grafie, nic z tego nie będzie");
        }

        int konserwa = 0;
        for (Place place : this.placeSet) {
            konserwa += weights.get(place) * place.resources;
        }

        Collection<Map<Place, Integer>> markings = getCoverabilityGraph().getVertices();
        for (Map<Place, Integer> marking : markings) {
            if (marking.containsValue(-1)) {
                System.out.println("Graf pokrycia jest nieskończony, sieć nie jest zachowawcza");
                return false;
            }
            int sum = 0;
            for (Place place : this.placeSet) {
                sum += weights.get(place) * marking.get(place);
            }
            if (sum != konserwa) {
                System.out.println("Suma znaczników nie jest stała, sieć nie jest zachowawcza");
                return false;
            }
        }
        return true;

    }

    public boolean getSimpleGraphConservation() {
        Map<Place, Integer> weights = new HashMap();
        for (Place place : this.placeSet) {
            weights.put(place, 1);
        }
        if (!weights.keySet().equals(this.placeSet)) {
            System.out.println("Mapa wag nie odpowiada miejscom w grafie, nic z tego nie będzie");
        }

        int konserwa = 0;
        for (Place place : this.placeSet) {
            konserwa += weights.get(place) * place.resources;
        }

        Collection<Map<Place, Integer>> markings = getCoverabilityGraph().getVertices();
        for (Map<Place, Integer> marking : markings) {
            if (marking.containsValue(-1)) {
                System.out.println("Graf pokrycia jest nieskończony, sieć nie jest zachowawcza");
                return false;
            }
            int sum = 0;
            for (Place place : this.placeSet) {
                sum += weights.get(place) * marking.get(place);
            }
            if (sum != konserwa) {
                System.out.println("Suma znaczników nie jest stała, sieć nie jest zachowawcza");
                return false;
            }
        }
        return true;

    }

    public boolean getGraphReversibility() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        DijkstraShortestPath<Map<Place, Integer>, Transition> alg = new DijkstraShortestPath(cg);
        Map<Place, Integer> currentmarking = this.getMarking();
        for (Map<Place, Integer> marking : cg.getVertices()) {
            if (alg.getPath(marking, currentmarking).isEmpty() && !marking.equals(currentmarking)) {
                return false;
            }
        }
        return true;
    }

    public Set<Integer> getTransitionIds() {
        Set<Integer> idSet = new HashSet();
        for (Transition transition : transitionSet) {
            idSet.add(transition.getId());
        }
        return idSet;
    }

    public Transition getTransitionById(int id) {
        for (Transition transition : transitionSet) {
            if (transition.getId() == id) {
                return transition;
            }
        }
        return null;
    }

    public static boolean isTransitionInSetById(Collection<Transition> transitions, Transition t) {
        for (Transition transition : transitions) {
            if (transition.getId() == t.id) {
                return true;
            }
        }
        return false;
    }

    /**
     * L1 żywotność - dla każdego przejścia istnieje ciąg przejść od znakowania
     * początkowego, w którym ono występuje czyli czy każde przejście jest w
     * grafie pokrycia
     *
     * @return L1 zywotnosc
     */
    public boolean getGraphL1Liveness() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        Set<Transition> transitions = this.transitionSet;
        for (Transition transition : transitions) {
            //tak się nie da bo w grafie pokrycia są nowe obiekty
            //if (!cg.getEdges().contains(transition)) {
            if (!PetriGraph.isTransitionInSetById(cg.getEdges(), transition)) {
                return false;
            }
        }
        return true;
    }
    
    public void calculateAndSetGraphL1Liveness() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        Set<Transition> transitions = this.transitionSet;
        for (Transition transition : transitions) {
            //tak się nie da bo w grafie pokrycia są nowe obiekty
            //if (!cg.getEdges().contains(transition)) {
            if (!PetriGraph.isTransitionInSetById(cg.getEdges(), transition)) {
                transition.setL1alive(false);
            } else {
                transition.setL1alive(true);
            }
        }
    }

    /**
     * L4 żywotność (czyli pełna żywotność) - dla każdego przejścia zawsze (z
     * każdego osiągalnego znakowania) da się wykonać to przejście czyli w
     * grafie pokrycia z każdego znakowania wychodzą wszystkie przejścia
     *
     * @return L4 żywotnosc
     */
    public boolean getGraphL4Liveness() {
        DirectedSparseMultigraph<Map<Place, Integer>, Transition> cg = this.getCoverabilityGraph();
        Set<Transition> transitions = this.transitionSet;
        for (Map<Place, Integer> marking : cg.getVertices()) {
            for (Transition transition : transitions) {
                //tak się nie da bo w grafie pokrycia są nowe obiekty
                //if (!cg.getOutEdges(marking).contains(transition)) {
                if (!PetriGraph.isTransitionInSetById(cg.getOutEdges(marking), transition)) {
                    return false;
                }
            }
        }

        return true;
    }
}
