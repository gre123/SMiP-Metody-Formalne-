package smip.views;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;
import javax.swing.JFrame;
import model.Place;
import org.apache.commons.collections15.Transformer;
import painter.LongRectangleShapePainter;
import painter.WhiteColorPainter;

public class ShowGraph {

    private static JFrame graphFrame = null;
    private static Point location;

    public static void setLocation(Point location) {
        ShowGraph.location = location;
    }

    public static <V, E> void showGraph(Graph<V, E> g, String windowName, int dimensionX, int dimensionY) {
        if (graphFrame == null) {
            graphFrame = new JFrame();
            graphFrame.setLocation(location);
        }

        graphFrame.setAlwaysOnTop(true);
        Layout<V, E> layout = new ISOMLayout<>(g);
        layout.setSize(new Dimension(dimensionX, dimensionY));
        VisualizationViewer<V, E> vv;
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
        if (graphFrame == null) {
            graphFrame = new JFrame();
            graphFrame.setLocation(location);
        }
        graphFrame.setAlwaysOnTop(true);
        Layout<V, E> layout = new KKLayout<>(g);
        layout.setSize(new Dimension(dimensionX, dimensionY));
        VisualizationViewer<V, E> vv;
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

    /**
     * wyświetla graf osiągalności lub pokrycia
     *
     * @param <E> klasa łuku
     * @param g graf
     * @param vlt labeller wierzchołków
     * @param windowName nazwa okna
     * @param dimensionX rozmiar okna w poziomie
     * @param dimensionY rozmiar okna w pionie
     */
    public static <E> void showRCGraph(Graph<Map<Place, Integer>, E> g, Transformer<Map<Place, Integer>, String> vlt, String windowName, int dimensionX, int dimensionY) {
        if (graphFrame == null) {
            graphFrame = new JFrame();
            graphFrame.setLocation(location);
        }
        graphFrame.setAlwaysOnTop(true);
        Layout<Map<Place, Integer>, E> layout = new ISOMLayout<>(g);
        layout.setSize(new Dimension(dimensionX, dimensionY));
        VisualizationViewer<Map<Place, Integer>, E> vv;
        vv = new VisualizationViewer<>(layout,
                new Dimension(dimensionX, dimensionY));
        vv.setPreferredSize(new Dimension(dimensionX, dimensionY));
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(vlt);
        vv.getRenderContext().setVertexShapeTransformer(new LongRectangleShapePainter());
        vv.getRenderContext().setVertexFillPaintTransformer(new WhiteColorPainter());
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
