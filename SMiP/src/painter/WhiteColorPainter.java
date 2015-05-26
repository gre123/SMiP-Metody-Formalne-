package painter;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class WhiteColorPainter<V> implements Transformer<V, Paint> {

    @Override
    public Paint transform(V v) {
        return Color.WHITE;
    }
}
