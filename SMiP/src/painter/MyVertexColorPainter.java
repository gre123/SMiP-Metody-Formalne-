package painter;

import org.apache.commons.collections15.Transformer;
import java.awt.*;
import model.MyVertex;
import model.Place;
import model.Transition;

public class MyVertexColorPainter implements Transformer<MyVertex, Paint> {

    @Override
    public Paint transform(MyVertex v) {
        if (v.getClass() == Place.class) {
            return Color.YELLOW;
        } else if (v.getClass() == Transition.class) {
            if (((Transition) v).getActive()) {
                return Color.GREEN;
            } else {
                return Color.RED;
            }
        }
        return Color.GRAY;
    }
}
