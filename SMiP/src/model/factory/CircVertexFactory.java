
package model.factory;

import model.Node;
import model.NodeShape;
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
    public Node create() {
        return new Node((n++), NodeShape.CIRCLE);
    }
    public Node create(float iks, float ygr) {
        return new Node((n++), iks, ygr, NodeShape.CIRCLE);
    }
}
