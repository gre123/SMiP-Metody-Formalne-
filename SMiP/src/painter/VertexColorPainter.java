/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import Struktura_jung.node;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

//Here we want to dictate how our nodes are painted. The Metadata which we are reading in from our GraphML file is a color
// value. So we write a vertex painter which we will pass to our VisualizationViewer. This way when Jung goes to draw our
// nodes, it will check this function and paint the nodes accordingly.

public class VertexColorPainter implements Transformer<node, Paint> {

    @Override
    public Paint transform(node v) //So for each node that we draw...
    {
        Color color = null;
        int kolor = v.getColor();
        switch (kolor) {
            case 0:
                color = Color.YELLOW;
                break;
            case 1:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.GREEN;
                break;                
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.GRAY;
                break;
            case 5:
                color = Color.CYAN;
                break;
            case 6:
                color = Color.WHITE;
                break;
            case 7:
                color = Color.MAGENTA;
                break;
            case 8:
                color = Color.ORANGE;
                break;
            case 9:
                color = Color.PINK;
                break;
            case 10:
                color = Color.RED;
                break;
            case 11:
                color = Color.LIGHT_GRAY;
                break;
            case 12:
                color = Color.DARK_GRAY;
                break;
            default:
                color = Color.DARK_GRAY;
                break;
        }
        return color;
    }
}