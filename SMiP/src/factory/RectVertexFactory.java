package factory;

import model.Node;
import model.NodeShape;
import org.apache.commons.collections15.Factory;

public class RectVertexFactory implements Factory {

    private static int n = 1;

    public static void reset() {
        n = 1;
    }

    @Override
    public Node create() {
        return new Node((n++), NodeShape.RECTANGLE);
    }
    public Node create(float iks, float ygr) {
        return new Node((n++), iks, ygr, NodeShape.RECTANGLE);
    }
}
