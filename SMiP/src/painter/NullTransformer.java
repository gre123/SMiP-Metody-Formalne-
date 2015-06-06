package painter;

import org.apache.commons.collections15.Transformer;

public class NullTransformer<V> implements Transformer<V, String> {

    @Override
    public String transform(V v) {
        return "";
    }

};
