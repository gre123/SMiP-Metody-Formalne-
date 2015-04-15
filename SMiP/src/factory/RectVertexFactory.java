
package factory;

import model.node;
import model.nodeShape;
import org.apache.commons.collections15.Factory;

public class RectVertexFactory implements Factory {

    private static int n = 0;

    public static void zeruj() {
        n = 0;
    }

    @Override
    public node create() {
        return new node((n++), nodeShape.RECTANGLE);
    }
    public node create(float iks, float ygr) {
        return new node((n++), iks, ygr, nodeShape.RECTANGLE);
    }
}
