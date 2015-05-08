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
public class Transition extends MyVertex{
    boolean Active;

    public Transition(int id) {
        super(id, "Transition");
        this.id = id;
    }
    public String toString(){
        return "T"+id;
    }

    public boolean getActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }
    
}
