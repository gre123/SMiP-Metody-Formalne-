package mouse.PopupMenu;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class DeleteVertexMenuItem<V> extends JMenuItem implements VertexMenuListener<V> {
    private V vertex;
    private VisualizationViewer visComp;
    
    public DeleteVertexMenuItem() {
        super("Delete Vertex");
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                visComp.getPickedVertexState().pick(vertex, false);
                visComp.getGraphLayout().getGraph().removeVertex(vertex);
                visComp.repaint();
            }
        });
    }

    /**
     * Implements the VertexMenuListener interface.
     * @param v 
     * @param visComp 
     */
    @Override
    public void setVertexAndView(V v, VisualizationViewer visComp) {
        this.vertex = v;
        this.visComp = visComp;
        this.setText("Delete Vertex " + v.toString());
    }
    
}
