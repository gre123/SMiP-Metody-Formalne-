/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import edu.uci.ics.jung.graph.Graph;
import java.util.Comparator;

/**
 *
 * @author Elpidiusz
 */
public class MyVertex implements Comparable<MyVertex>{
    int id;
    String description;
    
    public MyVertex(int id, String description){
        this.id = id;
        this.description = description;
    }
    public MyVertex(int id){
        this.id = id;
    }

    public int compareTo(MyVertex v2) {
        return ((Integer)this.id).compareTo((Integer)v2.id);    }
}
