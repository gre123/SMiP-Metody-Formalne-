package smip;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import model.node;

public class SMiP {

    public static VisualizationViewer<node, Integer> viewer=null;
    public static DirectedSparseGraph<node,Integer> graphNet=null; 
    public static MainGUI mainGUI= null;

    public static void main(String[] args) {
        mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
    
}
