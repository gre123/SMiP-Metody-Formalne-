/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import Struktura_jung.node;
import model.nodeShape;
import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabryka wierzchołków. Robi wierzchołki z kolejnymi ID od 0 wzwyż.
 *
 * @author Epifaniusz
 */
public class CircVertexFactory implements Factory {

    private static int n = 0;

    public static void zeruj() {
        n = 0;
    }

    public node create() {
        return new node((new Integer(n++)), nodeShape.CIRCLE);
    }
    public node create(float iks, float ygr) {
        return new node((new Integer(n++)), iks, ygr, nodeShape.CIRCLE);
    }
}
