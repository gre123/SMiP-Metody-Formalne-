package model;


import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.util.HashSet;
import java.util.Set;

public class ReachabilityGraph extends DirectedSparseGraph<ReachabilityVertex, ReachabilityArc> {
    private Set<ReachabilityVertex> vertexSet;

    public ReachabilityGraph() {
        vertexSet = new HashSet<>();
    }

    public boolean checkIsExistsVertex(ReachabilityVertex vertex) {
        return vertexSet.contains(vertex);
    }

    @Override
    public boolean addVertex(ReachabilityVertex vertex) {
        vertexSet.add(vertex);
        return super.addVertex(vertex);
    }
}
