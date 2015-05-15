/*
 */
package model;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Dimension;
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
import javax.swing.JFrame;
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
     * Testowa wersja, nie działa poprawnie Gdyby -1 się magicznie nie
     * zamieniały na 100 to by było w porządku -1 == inf Zostawiam ten kod zeby
     * moc sobie tu spojrzec jakby mi sie kiedys zaczelo wydawac ze umiem
     * napisac prosta funkcje w javie
     *
     * @return graf pokrycia (tak serio to nie)
     */
    public DirectedSparseGraph<Map<Place, Integer>, Transition> getCoverabilityGraph() {
        DirectedSparseGraph<Map<Place, Integer>, Transition> rg = new DirectedSparseGraph<>();
        Map<Place, Integer> baseMarking = this.getMarking();
        LinkedList<Map.Entry<Map<Place, Integer>, Transition>> transitionsToCheck = new java.util.LinkedList<>();

        for (Transition t : this.getActiveTransitions()) {
            transitionsToCheck.add(new SimpleEntry<>(baseMarking, t));
        }
        rg.addVertex(baseMarking);
        int count = 0;
        while (!transitionsToCheck.isEmpty() && count < 300) {
            ArrayList<Place> infPlaces = new ArrayList<Place>();
            Map.Entry<Map<Place, Integer>, Transition> entry = transitionsToCheck.poll();
            System.out.println("Przerabiam przejście: " + entry.toString());
            boolean minussto = false;
            for (Entry<Place, Integer> m : entry.getKey().entrySet()) {
                if (m.getValue().equals(-1)) {
                    minussto = true;
                    infPlaces.add(m.getKey());
                    entry.getKey().put(m.getKey(), 100);//żeby nie ustawiać -1 w kolejnej linijce
                }
            }
            if (minussto) {
                System.out.println("  Zamieniłem -1 na 100: " + entry.toString());
            }
            this.setMarking(entry.getKey());
            this.executeTransition(entry.getValue());
            System.out.println("  Przejście wykonane: " + this.getMarking().toString());
            Map<Place, Integer> currentMarking = this.getMarking();

            boolean minusjeden = false;
            for (Place p : infPlaces) {
                if (!currentMarking.keySet().contains(p)) {
                    System.out.println("jeśli widzisz ten tekst to coś jest nie tak");
                }
                System.out.println("  Przywracam -1 do p= " + p.toString());
                currentMarking.put(p, -1);
                minusjeden = true;
            }
            if (minusjeden) {
                System.out.println("  Skonczylem przywracać -1:" + currentMarking.toString());
            }
            if (!rg.containsVertex(currentMarking)) {
                System.out.println(" Takiego znakowania w grafi nie ma, bede wsadzać: " + currentMarking.toString());
                System.out.println("              (bo graf zawiera: " + rg.getVertices().toString() + ")");
                for (Map<Place, Integer> znakowanie : rg.getVertices()) {
                    if (isMore(currentMarking, znakowanie)) {
                        System.out.println("  ale jest mniejsze, więc ustawiam inf: " + currentMarking.toString());
                    }
                }
                rg.addVertex(currentMarking);
                for (Transition t : this.getActiveTransitions()) {
                    transitionsToCheck.add(new SimpleEntry<>(currentMarking, t));
                    System.out.println("    Dodaję przejście do sprawdzenia: " + currentMarking.toString() + "->" + t);
                }
            }
            System.out.println(" Wsadzam połączenie: " + entry.getValue() + " między " + entry.getKey().toString() + " a " + currentMarking.toString());
            System.out.println(" po czym graf ma takie wierzchołki: " + rg.getVertices().toString());
            rg.addEdge(new Transition(entry.getValue().id), entry.getKey(), currentMarking);
            infPlaces.clear();
            count++;
        }
        this.setMarking(baseMarking);
        return rg;
    }

    /**
     * Chyba dziala dobrze -1 == inf
     *
     * @return graf pokrycia (if you are very lucky)
     */
    public DirectedSparseGraph<Map<Place, Integer>, Transition> getCoverabilityGraphv2() {
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

    /**
     * Funkcja do niczego nie potrzebna, zostawiam w podobnym celu jak
     * getCoverabilityGraph
     *
     * @return same result as rg.containsVertex(currentMarking)
     */
    public static boolean doesReallyContainVertex(DirectedSparseGraph<Map<Place, Integer>, Transition> rg, Map<Place, Integer> currentMarking) {
        boolean thesame = true;
        for (Map<Place, Integer> marking : rg.getVertices()) {
            for (Place p : marking.keySet()) {
                if (marking.get(p) != currentMarking.get(p)) {
                    thesame = false;
                    break;
                }
            }
            if (thesame) {
                return true;
            }
        }
        return false;
    }

}
