/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elpidiusz
 */
public class Place extends MyVertex{
    boolean isAlive;
    int resources;
    int capacity;

    public Place(int id) {
        super(id,"Place");
        resources = 0;
    }
    public Place(int id, String desc) {
        super(id, desc);
        resources = 0;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        if (resources >=0 ){
            this.resources = resources;
        } else {
            throw new IllegalArgumentException("nie można ustawić ujemnej ilości znaczników");
        }
    }
    
    public void incResources() {
        resources++;
    }
    
    public void decResources() {
        if (resources>0) {
            resources--;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String toString(){
        return "P"+id+":"+resources;
    }
    
    
}
