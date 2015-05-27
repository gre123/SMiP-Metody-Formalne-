package painter;

import model.MyVertex;
import model.Place;
import model.Transition;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author Elpidiusz
 */
public class BoundednessLabeller implements Transformer<MyVertex, String> {

    @Override
    public String transform(MyVertex i) {
        if (i.getClass() == Transition.class) {
            return i.toString();
        }
        //return /*"P" + i.getId() + ":" +*/ ""+((Place) i).getBoundary();
        return ""+ ((((Place) i).getBoundary() == -1) ? "∞ " : ((Place) i).getBoundary());
    }

}
