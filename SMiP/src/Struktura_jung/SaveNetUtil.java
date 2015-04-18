package Struktura_jung;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node;
import smip.SMiP;


public class SaveNetUtil {
    public static void saveNet(String path) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(SMiP.graphNet);
    }
    
    public static void loadNet(String path) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        SMiP.graphNet = (DirectedSparseGraph<node,Integer>) ois.readObject();
        SMiP.viewer.setGraphLayout(new ISOMLayout(SMiP.graphNet));
        SMiP.viewer.repaint();
    }
    
}
