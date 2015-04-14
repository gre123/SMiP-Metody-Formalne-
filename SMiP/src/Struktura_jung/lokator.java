/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Struktura_jung;

import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class lokator implements Transformer<node, Point2D> {

    @Override
    public Point2D transform(node w) {
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