package CheckingMouse;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import CheckingMouse.EditingCheckingGraphMousePlugin.EdgeChecker;
import javax.swing.JOptionPane;
import model.Arc;
import model.MyVertex;

/**
 * @author Elpidiusz
 */
    public class ArcChecker implements EdgeChecker<MyVertex, Arc> {

        @Override
        public boolean checkEdge(Graph<MyVertex, Arc> g, VisualizationViewer<MyVertex, Arc> vv, Arc edge, MyVertex start, MyVertex end, EdgeType dir) {
            if (start.getClass() == end.getClass()) {
                JOptionPane.showMessageDialog(vv, "E, łączyć można tylko wierzchołki różnych typów",
                        "Arc check", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (g.findEdge(start, end) != null) {//może się przydać
                JOptionPane.showMessageDialog(vv, "No parallel edges allowed in this graph!",
                        "Arc Check", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;

        }
        
    }
