package painter;

import model.Node;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import model.NodeShape;
import org.apache.commons.collections15.Transformer;

public class VertexShapePainter implements Transformer<Node, Shape>{

    @Override
    public Shape transform(Node v) {
        Area shape = null;
        Random random = new Random();
        
        if(v.getShape()==NodeShape.RECTANGLE){
            shape = new Area(new Rectangle(-10,-10, 20, 40));
        }
        
        else{
            if(v.getShape()==NodeShape.CIRCLE){
                shape = new Area(new Ellipse2D.Double(-10, -10, 20, 20));
                if(v.getMarkCount()!=0) {
                    for(int i=0;i<v.getMarkCount();i++){
                        shape.exclusiveOr(new Area(new Ellipse2D.Double(-7 +random.nextInt(12), -7 +random.nextInt(12), 1, 1)));
                    }
                }
            }
        }
        return shape;
    }
}
