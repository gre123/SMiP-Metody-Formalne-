
package factory;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabryka wierzchołków. Robi wierzchołki z kolejnymi ID od 0 wzwyż.
 *
 * @author Epifaniusz
 */
public class PlaceTransitionFactory implements Factory {

    private static int place_n = 0;
    private static int transition_n = 0;

    public static void zeruj() {
        place_n = 0;
        transition_n = 0;
    }

    @Override
    /**
     * DO NOT USE, use create(boolean place to get type of vertex you wants)
     */
    public Place create() {
        return new Place((place_n++), "factorizedPlace");
    }
    
    //to nie powinno brać booleana tylko jakieś MyVertex.class ale nie mam internetu i nie wiem jak się tego używa
    public MyVertex create(Class type) {
        if(type == Place.class){
            return new Place((place_n++), "factorizedPlace");
        }else if(type == Transition.class){
            return new Transition(transition_n++);
        }
        return null; //wypada rzucić jakimś wyjątkiem tutaj
    }
}
