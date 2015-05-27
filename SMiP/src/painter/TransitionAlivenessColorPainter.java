package painter;

import org.apache.commons.collections15.Transformer;

import java.awt.*;
import model.MyVertex;
import model.Place;
import model.Transition;

public class TransitionAlivenessColorPainter implements Transformer<MyVertex, Paint> {

    @Override
    public Paint transform(MyVertex v) {
        if (v.getClass() == Place.class) {
            return Color.WHITE;
        } else if (v.getClass() == Transition.class) {
            if (((Transition) v).isL1alive()) {
                return Color.GREEN;
            } else {
                return Color.darkGray;
            }
        }
        return Color.PINK;
    }
}
