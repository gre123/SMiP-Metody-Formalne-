/*
 */
package model;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import java.util.Collections;
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

/**
 * A Bipartite graph is divided into A vertices and B vertices. Edges only
 * connect A vertices to B vertices, and vice versa. This class extends
 * <i>UndirectedSparseGraph</i>; thus, the Graph is made up of
 * <i>UndirectedSparseVertices</i>.
 * <p>
 * Vertices can only be added to the graph with a flag that says which class
 * they will be added to (using <i>BipartiteGraph.Choice</i> ); edges must be of
 * type <i>BipartiteGraph</i>, which must consist of two vertices, one each from
 * CLASSA and CLASSB.
 * <pre>
 * BipartiteGraph bpg = new BipartiteGraph;
 * Vertex va = bpg.addVertex( new UndirectedSparseVertex(), BipartiteGraph.CLASSA );
 * Vertex vb = bpg.addVertex( new UndirectedSparseVertex(), BipartiteGraph.CLASSB );
 * bpg.addBipartiteEdge( new BipartiteEdge( va, vb ));
 * </pre> Note that the traditional <i>addVertex()</i> and <i>addEdge()</i> will
 * both throw a <i>FatalException</i>.<p>
 * The function <i>fold</i> creates an <i>UndirectedGraph</i>
 * based on finding vertices that share a common neighbor.
 *
 * @author danyelf
 * @since 1.0.1
 */
public class PetriGraph extends DirectedSparseGraph<MyVertex, Arc> {

    private Set placeSet = new HashSet();
    private Set transitionSet = new HashSet();

    public void initialize() {
        placeSet = new HashSet();
        transitionSet = new HashSet();
    }
    

    public PetriGraph(){
        super();
        placeSet = new HashSet();
        transitionSet = new HashSet();
    }
    /**
     * Returns the set of all vertices from that class. All vertices in the
     * return set will be of class A or class B, depending on the parameter.
     */
    public Set getAllVertices(Choice choice) {
        if (choice == CLASSA) {
            return Collections.unmodifiableSet(placeSet);
        } else if (choice == CLASSB) {
            return Collections.unmodifiableSet(transitionSet);
        } else {
            throw new IllegalArgumentException("Invalid partition specification " + choice);
        }
    }

    /**
     * Returns the partition for vertex <code>v</code>.
     *
     * @param v
     */
    public Choice getPartition(MyVertex v) {
        if (placeSet.contains(v)) {
            return CLASSA;
        } else if (transitionSet.contains(v)) {
            return CLASSB;
        } else {
            if (!this.containsVertex(v)) {
                throw new IllegalArgumentException("Inconsistent state in graph!");
            }
            throw new IllegalArgumentException("Vertex " + v + " is not part of this graph");
        }
    }

    /**
     * Adds a single vertex to the graph in the specified partition. Note that
     * the vertex must be compatible with BipartiteVertex.
     *
     * <p>
     * Throws an <code>IllegalArgumentException</code> if <code>v</code> is not
     * an element of either partition.</p>
     *
     * @param v	the vertex to be added to the class
     * @param choice	the class to which the vertex should be added
     * @return the input vertex
     */
    public MyVertex addVertex(MyVertex v, Choice choice) {
        String exists = "Specified partition already contains vertex ";
        String dup = "Another partition already contains vertex ";
        if (choice == CLASSA) {
            if (placeSet.contains(v)) {
                throw new IllegalArgumentException(exists + v);
            }
            if (transitionSet.contains(v)) {
                throw new IllegalArgumentException(dup + v);
            }
            placeSet.add(v);
        } else if (choice == CLASSB) {
            if (transitionSet.contains(v)) {
                throw new IllegalArgumentException(exists + v);
            }
            if (placeSet.contains(v)) {
                throw new IllegalArgumentException(dup + v);
            }
            transitionSet.add(v);
        } else {
            throw new IllegalArgumentException("Invalid partition specification for vertex " + v + ": " + choice);
        }
        super.addVertex(v);
        return v;
    }

//    /**
//     * Adds a BipartiteEdge to the Graph. This function is simply a typed
//     * version of addEdge
//     *
//     * @param bpe a BipartiteEdge
//     * @return the edge, now a member of the graph.
//     */
//    public Edge addBipartiteEdge(Edge bpe) {
//        return super.addEdge(bpe);
//    }
//
//    /**
//     * DO NOT USE THIS METHOD. Contractually required, but merely throws a
//     * FatalException.
//     *
//     * @see
//     * edu.uci.ics.jung.graph.impl.UndirectedSparseGraph#addEdge(edu.uci.ics.jung.graph.Edge)
//     * @deprecated Use addBipartiteEdge
//     */
//    public Edge addEdge(Edge ae) throws Exception {
//        throw new Exception("Only add BipartiteEdges");
//    }
//
//    /**
//     * DO NOT USE THIS METHOD. Contractually required, but merely throws a
//     * FatalException.
//     *
//     * @see
//     * edu.uci.ics.jung.graph.impl.UndirectedSparseGraph#addVertex(edu.uci.ics.jung.graph.Vertex)
//     * @deprecated Use addBipartiteVertex
//     */
//    public Vertex addVertex(Vertex av) throws Exception {
//        throw new Exception("Use addVertexX to add vertices to a BipartiteGraph ");
//    }

    /**
     * This small enumerated type merely forces a user to pick class "A" or "B"
     * when adding a Vertex to a BipartiteGraph.
     */
    public static final class Choice {
    }
    public static final Choice CLASSA = new Choice();
    public static final Choice CLASSB = new Choice();

    /**
     * The tag for the UserData attached to a single Edge.
     */
    public static final Object BIPARTITE_USER_TAG = "BipartiteUserTag";

    /**
     * Adds all pairs (key, value) to the multimap from the initial set keySet.
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

    /* (non-Javadoc)
     * @see edu.uci.ics.jung.graph.Graph#removeVertex(edu.uci.ics.jung.graph.Vertex)
     */
    @Override
    public boolean removeVertex(MyVertex v) {
        if (placeSet.contains(v)) {
            placeSet.remove(v);
        } else {
            transitionSet.remove(v);
        }
        return super.removeVertex(v);
    }
    
    boolean countTransitionAlive(Transition t){
        boolean alive = true;
        for(Object transition:this.getPredecessors(t)){
            if (((Place)transition).getResources()<1){
                alive=false;
            }
        }
        return alive;
    }
    public boolean isGraphAlive(){
        boolean alive = true;
        for(Object transition:this.getAllVertices(CLASSB)){
            if (!((Transition)transition).getActive()){
                alive=false;
            }
        }
        return alive;
    }

}
