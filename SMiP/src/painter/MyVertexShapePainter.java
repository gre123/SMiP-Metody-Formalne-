package painter;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

public class MyVertexShapePainter implements Transformer<MyVertex, Shape>{

    @Override
    public Shape transform(MyVertex v)
    {
        if (v.getClass() == Place.class) {
            return new Area(new Ellipse2D.Float(-15, -15, 30, 30));
        } else if (v.getClass() == Transition.class) {
            return new Area(new Rectangle(-5,-5, 10, 30));
        }
        return new Area(new Ellipse2D.Float(-5, -10, 10, 20));//nie powinno takich wierzchołków być
    }
}
