
package model.factory;

import model.Arc;
import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabryka wierzchołków. Robi wierzchołki z kolejnymi ID od 0 wzwyż.
 *
 * @author Epifaniusz
 */
public class ArcFactory implements Factory {

    private static int n = 0;

    public static void zeruj() {
        n = 0;
    }

    @Override
    public Arc create() {
        return new Arc();
    }
}
