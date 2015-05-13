package painter;


import model.ReachabilityVertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class ReachabilityGraphVertexShapePainter implements Transformer<ReachabilityVertex, Shape> {
    @Override
    public Shape transform(ReachabilityVertex reachabilityVertex) {
        return new Area(new Ellipse2D.Float(-15, -15, 30, 30));
    }
}
