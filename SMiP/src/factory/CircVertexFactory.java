
package factory;

import model.node;
import model.nodeShape;
import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabryka wierzchołków. Robi wierzchołki z kolejnymi ID od 1 wzwyż.
 *
 * @author Epifaniusz
 */
public class CircVertexFactory implements Factory {

    private static int n = 1;

    public static void zeruj() {
        n = 1;
    }

    @Override
    public node create() {
        return new node((n++), nodeShape.CIRCLE);
    }
    public node create(float iks, float ygr) {
        return new node((n++), iks, ygr, nodeShape.CIRCLE);
    }
}
