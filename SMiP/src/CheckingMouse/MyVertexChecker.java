package CheckingMouse;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import javax.swing.JOptionPane;
import model.Arc;
import model.MyVertex;
import model.PetriGraph;

/**
 * @author Elpidiusz
 */
    public class MyVertexChecker implements EditingCheckingGraphMousePlugin.VertexChecker<MyVertex,Arc> {

        public boolean checkVertex(Graph<MyVertex,Arc> g, VisualizationViewer<MyVertex,Arc> vv, MyVertex v) {
            // Will test to see if the graph has more that 5 vertices
            if (g.getVertexCount() < 7) {
                return true;
            } else {
                //tylko do testów
                if (g.getClass() == PetriGraph.class){
                    System.out.println(java.util.Arrays.deepToString(((PetriGraph)g).getNplus()));
                    System.out.println(java.util.Arrays.deepToString(((PetriGraph)g).getNminus()));
                    System.out.println(java.util.Arrays.deepToString(((PetriGraph)g).getNincidence()));
                    

                }
                JOptionPane.showMessageDialog(vv, "Hehe, zbyt skomplikowaną sieć byś chciał mieć, w tej wersji maksimum 5 wierzchołków.",
                        "Vertex Check", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
