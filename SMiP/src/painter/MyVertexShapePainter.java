package painter;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MyVertexShapePainter implements Transformer<MyVertex, Shape>{

    @Override
    public Shape transform(MyVertex v)
    {
        if (v.getClass() == Place.class) {
            Area shape = new Area(new Ellipse2D.Float(-15, -15, 30, 30));
            Random random = new Random();
            if (((Place) v).getResources() != 0) {
                for (int i = 0; i < ((Place) v).getResources(); i++) {
                    shape.exclusiveOr(new Area(new Ellipse2D.Double(-7 + random.nextInt(12), -7 + random.nextInt(12), 1, 1)));
                }
            }
            return shape;
        } else if (v.getClass() == Transition.class) {
            return new Area(new Rectangle(-5,-5, 10, 30));
        }
        return new Area(new Ellipse2D.Float(-5, -10, 10, 20));//nie powinno takich wierzchołków być
    }
}
