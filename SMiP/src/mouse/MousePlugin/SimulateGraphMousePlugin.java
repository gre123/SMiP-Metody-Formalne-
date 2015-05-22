package mouse.MousePlugin;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import model.PetriGraph;
import model.Transition;

public class SimulateGraphMousePlugin<V,E> extends PickingGraphMousePlugin<V,E> implements MouseListener{
    
    private final PetriGraph graph;
    public SimulateGraphMousePlugin(PetriGraph _graph){
        super();
        graph=_graph;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        final VisualizationViewer<V, E> viewer = (VisualizationViewer<V, E>)e.getSource();
        final Point2D p = e.getPoint();
        final Point2D ivp = p;
        GraphElementAccessor<V, E> pickSupport = viewer.getPickSupport();
        if(pickSupport != null) { 
            final V selectedTransition = pickSupport.getVertex(viewer.getGraphLayout(), ivp.getX(), ivp.getY());
            if(selectedTransition != null && selectedTransition.getClass()==Transition.class) {
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    graph.executeTransition((Transition) selectedTransition);
                }
            }
        }
    }
}

