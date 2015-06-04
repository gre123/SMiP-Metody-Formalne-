package painter;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MyVertexShapePainter implements Transformer<MyVertex, Shape> {

    @Override
    public Shape transform(MyVertex v) {
        int radius = 15;
        if (v.getClass() == Place.class) {

            Area shape = new Area(new Ellipse2D.Float(-radius, -radius, radius * 2, radius * 2));

            if (((Place) v).getResources() != 0) {
                float limit = 4;
                float currentradius = 2;
                int j = 0;
                for (int i = 0; i < ((Place) v).getResources(); i++) {
                    float angle = (float) (((limit - j) / limit) * Math.PI * 2);

                    double x = Math.cos(angle) * (currentradius);
                    double y = Math.sin(angle) * (currentradius);
                    shape.exclusiveOr(new Area(new Ellipse2D.Double(x, y, 1, 1)));
                    if ((j + 1) >= limit) {
                        currentradius += 3;
                        limit *= 1.75;
                        j = 0;
                    } else {
                        j++;
                    }
                }
            }
            return shape;
        } else if (v.getClass() == Transition.class) {
            return new Area(new Rectangle(-radius/2, -radius, radius, radius*2));
        }
        return new Area(new Ellipse2D.Float(-5, -10, 10, 20));//nie powinno takich wierzchołków być
    }
}
