package Struktura_jung;

import model.node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import java.awt.Dimension;
import painter.*;

public class Przerob {

    public static DirectedSparseGraph<node, Integer> getGraph() {  
        return new DirectedSparseGraph<>();
    }

    public static VisualizationViewer<node, Integer> getViewer(Graph g, String layout, int width, int height) {
        Layout<node, Integer> lay = null;
        if (layout.equalsIgnoreCase("ISOM")) {
            lay = new ISOMLayout(g);
        } else if (layout.equalsIgnoreCase("KK")) {
            lay = new KKLayout(g);
        } else if (layout.equalsIgnoreCase("FR")) {
            lay = new FRLayout(g);
        } else if (layout.equalsIgnoreCase("SPRING")) {
            lay = new SpringLayout(g);
        } else if (layout.equalsIgnoreCase("CIRCLE")) {
            lay = new CircleLayout(g);
        } else if (layout.equalsIgnoreCase("SPRING2")) {
            lay = new SpringLayout(g);
        } else if (layout.equalsIgnoreCase("static")) {
            lay=new StaticLayout<>(g, new lokator());
        }
        
        lay.setSize(new Dimension(width, height));
        VisualizationViewer<node, Integer> vv =
                new VisualizationViewer<>(lay,
                new Dimension(width, height));
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<node, Integer>());
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new VertexShapePainter());
        return vv;
    }
}
