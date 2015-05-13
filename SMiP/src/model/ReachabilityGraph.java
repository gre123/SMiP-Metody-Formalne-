package model;


import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.util.HashSet;
import java.util.Set;

public class ReachabilityGraph extends DirectedSparseGraph<ReachabilityVertex, ReachabilityArc> {
    private Set<ReachabilityVertex> vertexSet;

    public ReachabilityGraph() {
        vertexSet = new HashSet<>();
    }
}
