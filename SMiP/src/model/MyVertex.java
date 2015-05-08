/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import edu.uci.ics.jung.graph.Graph;

/**
 *
 * @author Elpidiusz
 */
public class MyVertex{
    int id;
    String description;
    
    public MyVertex(int id, String description){
        this.id = id;
        this.description = description;
    }
    public MyVertex(int id){
        this.id = id;
    }
}
