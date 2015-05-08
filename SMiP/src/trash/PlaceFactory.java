
package trash;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabryka wierzchołków. Robi wierzchołki z kolejnymi ID od 0 wzwyż.
 *
 * @author Epifaniusz
 */
public class PlaceFactory implements Factory {

    private static int n = 0;

    public static void zeruj() {
        n = 0;
    }

//    @Override
//    public Place create() {
//        return new Place((n++), "factorizedPlace");
//    }
    //tylko na próbę:
    @Override
    public MyVertex create() {
        if (n%2 ==0){
            return new Place((n++), "factorizedPlace");
        }
        return new Transition((n++));
    }
}
