/*
 */
package trash;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import javax.swing.JFrame;
import model.Arc;
import model.MyVertex;
import model.PetriGraph;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author Elpidiusz
 */
public class PetriGraphTest1 {

    public static void main(String[] args) {
        PetriGraph testgraph = new PetriGraph();
        Place jeden = new Place(1);
        testgraph.addVertex(jeden);
        Place dwa = new Place(2);
        testgraph.addVertex(dwa);
        testgraph.addVertex(new Place(3));
        testgraph.addVertex(new Transition(1));
        testgraph.addEdge(new Arc(), jeden, dwa);


        JFrame graphFrame = new JFrame();
        graphFrame.setAlwaysOnTop(true);

        Layout<MyVertex, Arc> layout = new CircleLayout(testgraph);
        layout.setSize(new Dimension(300, 300));
        VisualizationViewer<MyVertex, Arc> vv;
        vv = new VisualizationViewer<>(layout,
                new Dimension(350, 350));
        vv.setPreferredSize(new Dimension(350, 350));
        //graphFrame.setPreferredSize(new Dimension(350,350));
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);

        // Setup up a new vertex to paint transformer...
        Transformer<MyVertex, Paint> vertexPaint = (MyVertex i) -> {
            if (i.getClass() == Place.class){
                return Color.GREEN;
            } else if (i.getClass() == Transition.class){
                return Color.red;
            }
            return Color.pink;
        };
        // Set up a new stroke Transformer for the edges
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<Arc, Stroke> edgeStrokeTransformer
                = (Arc s) -> edgeStroke;
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

        graphFrame.getContentPane().add(vv);
        graphFrame.pack();
        graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphFrame.setTitle("Widok sieci");
        graphFrame.setVisible(true);
    }
}
