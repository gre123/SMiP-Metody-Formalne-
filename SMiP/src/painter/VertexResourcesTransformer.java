package painter;

import model.MyVertex;
import model.Place;
import org.apache.commons.collections15.Transformer;

public class VertexResourcesTransformer implements Transformer<MyVertex, String> {

    public String transform(MyVertex v) {
        if (v.getClass() == Place.class) {
            return Integer.toString(((Place) v).getResources());
        }
        return "";
    }

};
