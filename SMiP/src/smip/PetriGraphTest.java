/*
 * EditingGraphViewer.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */

package smip;

import CheckingMouse.ArcChecker;
import CheckingMouse.EditingCheckingGraphMousePlugin;
import CheckingMouse.EditingModalGraphMouse2;
import CheckingMouse.MyVertexChecker;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import factory.ArcFactory;
import factory.PlaceTransitionFactory;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import model.Arc;
import model.MyVertex;
import model.PetriGraph;
import org.apache.commons.collections15.Factory;
import painter.MyVertexColorPainter;
import painter.MyVertexShapePainter;


/**
 *
 * @author Dr. Greg M. Bernstein
 */
public class PetriGraphTest {
    PetriGraph testgraph = new PetriGraph();
    PetriGraph g;
    Factory <MyVertex> vertexFactory;
    Factory<Arc> edgeFactory;
    MyVertexChecker vCheck;
    ArcChecker eCheck;
    
    /** Creates a new instance of SimpleGraphView */
    public PetriGraphTest() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new PetriGraph();
        vertexFactory = new PlaceTransitionFactory();
        edgeFactory = new ArcFactory();
        vCheck = new MyVertexChecker();
        eCheck = new ArcChecker();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PetriGraphTest sgv = new PetriGraphTest();
        // Layout<V, E>, VisualizationViewer<V,E>
        Layout<MyVertex, Arc> layout = new StaticLayout(sgv.g);
        layout.setSize(new Dimension(500,500));
        VisualizationViewer<MyVertex, Arc> vv = new VisualizationViewer<MyVertex, Arc>(layout);
        vv.setPreferredSize(new Dimension(550,550));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexFillPaintTransformer(new MyVertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new MyVertexShapePainter());
        // Create a graph mouse and add it to the visualization viewer
        EditingModalGraphMouse2 gm = new EditingModalGraphMouse2(vv.getRenderContext(), 
                 sgv.vertexFactory, sgv.edgeFactory); 
        EditingCheckingGraphMousePlugin plugin = new EditingCheckingGraphMousePlugin(sgv.vertexFactory,
                sgv.edgeFactory);
        GraphMousePlugin oldPlugin = gm.getEditingPlugin(); //Remove current plugin
        gm.remove(oldPlugin);
        plugin.setVertexChecker(sgv.vCheck);
        plugin.setEdgeChecker(sgv.eCheck);
        gm.setEditingPlugin(plugin);
        vv.setGraphMouse(gm);

        
        JFrame frame = new JFrame("Test edytora grafu z weryfikacją");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        
        // Let's add a menu for changing mouse modes
        JMenuBar menuBar = new JMenuBar();
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null); // I'm using this in a main menu
        modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
        
        menuBar.add(modeMenu);
        frame.setJMenuBar(menuBar);
        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
        frame.pack();
        frame.setVisible(true);  
        
    }
}
