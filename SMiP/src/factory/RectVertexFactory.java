
package factory;

import Struktura_jung.node;
import model.nodeShape;
import org.apache.commons.collections15.Factory;

public class RectVertexFactory implements Factory {

    private static int n = 0;

    public static void zeruj() {
        n = 0;
    }

    public node create() {
        return new node((new Integer(n++)), nodeShape.RECTANGLE);
    }
    public node create(float iks, float ygr) {
        return new node((new Integer(n++)), iks, ygr, nodeShape.RECTANGLE);
    }
}
