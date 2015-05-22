
package jung;

import model.Node;
import org.apache.commons.collections15.Transformer;

import java.awt.geom.Point2D;
import java.util.Random;

public class Lokator implements Transformer<Node, Point2D> {

    @Override
    public Point2D transform(Node w) {
        //zakomentowane, bo zmniejszenie trójkąta powoduje, że już się nie wyświetla planarnie, nie mam pomysłu na przeskalowanie tego
        //return new Point2D.Float((w.getX()>1.1) ? w.getX() : w.getX()*580 , (w.getY()>1.1) ? w.getY() : w.getY()*480);
        if((w.getX()==0.0) && (w.getY()==0.0)){
            Random r = new Random();
            w.setX(r.nextFloat());
            w.setY(r.nextFloat());
        }
        return new Point2D.Float(w.getX()*703+7,w.getY()*430+7);
    
    }
}