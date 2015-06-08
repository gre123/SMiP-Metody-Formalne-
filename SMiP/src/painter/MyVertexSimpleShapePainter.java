package painter;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class MyVertexSimpleShapePainter implements Transformer<MyVertex, Shape> {

    @Override
    public Shape transform(MyVertex v) {
        int radius = 15;
        if (v.getClass() == Place.class) {
            return new Ellipse2D.Float(-radius, -radius, radius * 2, radius * 2);
        } else if (v.getClass() == Transition.class) {
            return new Area(new Rectangle(-radius/2, -radius, radius, radius*2));
        }
        return new Area(new Ellipse2D.Float(-5, -10, 10, 20));//nie powinno takich wierzchołków być
    }
}
