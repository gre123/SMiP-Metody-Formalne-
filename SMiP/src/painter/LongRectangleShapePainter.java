package painter;

import model.Place;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.util.Map;

public class LongRectangleShapePainter implements Transformer<Map<Place, Integer>, Shape> {

    @Override
    public Shape transform(Map<Place, Integer> v) {
        return new Rectangle.Float(0, 0, 10 + 10 * v.size(), 18);
    }
}
