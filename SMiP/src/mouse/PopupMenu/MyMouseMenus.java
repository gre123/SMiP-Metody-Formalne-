package mouse.PopupMenu;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import model.Place;

public class MyMouseMenus {

    public static class VertexMenu extends JPopupMenu {

        public VertexMenu(final JFrame frame) {
            super("Vertex Menu");
            this.add(new DeleteVertexMenuItem<>());
            this.addSeparator();
            this.add(new VertexPropItem(frame));
        }

    }

    public static class VertexPropItem extends JMenuItem implements VertexMenuListener<Place>,
            MenuPointListener {

        Place place;
        VisualizationViewer visComp;
        Point2D point;

        @Override
        public void setVertexAndView(Place place, VisualizationViewer visComp) {
            this.place = place;
            this.visComp = visComp;
        }

        @Override
        public void setPoint(Point2D point) {
            this.point = point;
        }

        public VertexPropItem(final JFrame frame) {
            super("Edit Vertex Properties...");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VertexPropertyDialog dialog = new VertexPropertyDialog(frame, place, visComp);
                    dialog.setLocation((int) point.getX() + 70, (int) point.getY());
                    dialog.setVisible(true);
                }
            });
        }
    }

    public static class ResourcesDisplay extends JMenuItem implements VertexMenuListener<Place> {

        @Override
        public void setVertexAndView(Place p, VisualizationViewer visComp) {
            this.setText("Resources " + p + " = " + p.getResources());
        }
    }

    public static class CapacityDisplay extends JMenuItem implements VertexMenuListener<Place> {

        @Override
        public void setVertexAndView(Place p, VisualizationViewer visComp) {
            this.setText("Capacity " + p + " = " + p.getCapacity());
        }
    }

    public static class EdgeMenu extends JPopupMenu {

        public EdgeMenu() {
            super("Edge Menu");
            this.add(new DeleteEdgeMenuItem<>());
        }
    }

}
