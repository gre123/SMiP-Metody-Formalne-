package CheckingMouse;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import model.Arc;
import model.MyVertex;

/**
 * @author Elpidiusz
 */
public class MyVertexChecker implements EditingCheckingGraphMousePlugin.VertexChecker<MyVertex, Arc> {

    public boolean checkVertex(Graph<MyVertex, Arc> g, VisualizationViewer<MyVertex, Arc> vv, MyVertex v) {
        return true;
    }
}
