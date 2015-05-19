
package MousePlugin;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import factory.CircVertexFactory;
import factory.RectVertexFactory;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import model.node;
import trash.SMiP;

public class PopupGraphMousePlugin extends AbstractPopupGraphMousePlugin
implements MouseListener {

    private CircVertexFactory placeFactory = new CircVertexFactory();
    private RectVertexFactory transitionFactory = new RectVertexFactory();
    
    public PopupGraphMousePlugin() {
        this(MouseEvent.BUTTON3_MASK);
    }
    public PopupGraphMousePlugin(int modifiers) {
        super(modifiers);
    }
    

    @Override
    protected void handlePopup(final MouseEvent e) {
        final VisualizationViewer<node, Integer> viewer = (VisualizationViewer<node, Integer>)e.getSource();
        final Point2D p = e.getPoint();
        final Point2D ivp = p;
        final Layout<node,Integer> layout = viewer.getGraphLayout();
        GraphElementAccessor<node, Integer> pickSupport = viewer.getPickSupport();
        if(pickSupport != null) { 
            JPopupMenu popup = new JPopupMenu();
            final node actualNode = pickSupport.getVertex(viewer.getGraphLayout(), ivp.getX(), ivp.getY());
            final Integer actualEdge = pickSupport.getEdge(viewer.getGraphLayout(), ivp.getX(), ivp.getY());
            if(actualNode != null) {
                popup.add(new AbstractAction("Usuń obiekt") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                SMiP.graphNet.removeVertex(actualNode);
                                SMiP.viewer.repaint();
                            }
                        });
                if(popup.getComponentCount() > 0) {
                    popup.show(viewer, e.getX(), e.getY());
                }
                
            }    
            else
            {
                if(actualEdge!=null)
                {
                    popup.add(new AbstractAction("Usuń krawędź") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Usuwanie krawedzi");
                            SMiP.graphNet.removeEdge(actualEdge);
                            SMiP.viewer.repaint();
                        }
                    });
                    if(popup.getComponentCount() > 0) {
                        popup.show(viewer, e.getX(), e.getY());
                    }
                }
                else
                {
                    popup.add(new AbstractAction("Dodaj miejsce") {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            node freshVertex = placeFactory.create();
                            SMiP.graphNet.addVertex(freshVertex);
                            layout.setLocation(freshVertex, viewer.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
                            SMiP.viewer.repaint();         
                        }
                    });
                    popup.add(new AbstractAction("Dodaj przejście") {
                        public void actionPerformed(ActionEvent actionEvent) {
                            node freshVertex = transitionFactory.create();
                            SMiP.graphNet.addVertex(freshVertex);
                            layout.setLocation(freshVertex, viewer.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
                            SMiP.viewer.repaint();  
                        }
                    });
            
                    if(popup.getComponentCount() > 0) popup.show(viewer, e.getX(), e.getY());
                }
            }   
        
        }
        
    }
}

