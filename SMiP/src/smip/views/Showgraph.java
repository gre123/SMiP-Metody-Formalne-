/*
 */
package smip.views;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JFrame;
import model.Place;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author Elpidiusz
 */
public class Showgraph {
    public static <V, E> void showGraph(Graph<V, E> g, String windowName, int dimensionX, int dimensionY) {
        JFrame graphFrame = new JFrame();
        graphFrame.setAlwaysOnTop(true);
        Layout<V, E> layout = new KKLayout<>(g);
        layout.setSize(new Dimension(dimensionX, dimensionY));
        VisualizationViewer<V,E> vv;
        vv = new VisualizationViewer<>(layout,
                new Dimension(dimensionX, dimensionY));
        vv.setPreferredSize(new Dimension(dimensionX, dimensionY));
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.PICKING);
        vv.setGraphMouse(gm);
        graphFrame.getContentPane().add(vv);
        graphFrame.pack();
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graphFrame.setTitle(windowName);
        graphFrame.setVisible(true);
    }
    public static <V, E> void showGraph(Graph<V, E> g, Transformer<V, String> vlt, String windowName, int dimensionX, int dimensionY) {
        JFrame graphFrame = new JFrame();
        graphFrame.setAlwaysOnTop(true);
        Layout<V, E> layout = new KKLayout<>(g);
        layout.setSize(new Dimension(dimensionX, dimensionY));
        VisualizationViewer<V,E> vv;
        vv = new VisualizationViewer<>(layout,
                new Dimension(dimensionX, dimensionY));
        vv.setPreferredSize(new Dimension(dimensionX, dimensionY));
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(vlt);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.PICKING);
        vv.setGraphMouse(gm);
        graphFrame.getContentPane().add(vv);
        graphFrame.pack();
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graphFrame.setTitle(windowName);
        graphFrame.setVisible(true);
    }
}
