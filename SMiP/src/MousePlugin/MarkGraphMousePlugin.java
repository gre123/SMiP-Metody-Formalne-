
package MousePlugin;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import model.node;
import smip.SMiP;

public class MarkGraphMousePlugin extends PickingGraphMousePlugin
implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        final VisualizationViewer<node, Integer> viewer = (VisualizationViewer<node, Integer>)e.getSource();
        final Point2D p = e.getPoint();
        final Point2D ivp = p;
        GraphElementAccessor<node, Integer> pickSupport = viewer.getPickSupport();
        if(pickSupport != null) { 
            final node actualNode = pickSupport.getVertex(viewer.getGraphLayout(), ivp.getX(), ivp.getY());
            if(actualNode != null) {
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    actualNode.addMark();
                }
                else
                {
                    if(e.getButton()==MouseEvent.BUTTON2 && actualNode.hasMark())
                    {
                        actualNode.removeMark();
                    }
                }
                SMiP.viewer.repaint();  
            }
        }
    }
}