package painter;

import model.ReachabilityVertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class ReachabilityGraphVertexColorPainter implements Transformer<ReachabilityVertex, Paint> {
    @Override
    public Paint transform(ReachabilityVertex reachabilityVertex) {
        return Color.GRAY;
    }
}
