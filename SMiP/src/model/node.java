
package model;

import java.io.Serializable;

/**
 * Klasa reprezentująca wierzchołek. Ma kolor i niezmienialne po utworzeniu ID
 * (pasowałoby niepowtarzalne).
 *
 * @author Epifaniusz
 */


public class node implements Serializable{

    private final int nID;
    private float x;
    private float y;
    
    private int nKolor;
    private nodeShape shape;
    private int markCount=0;
    
    private boolean markWillBeAdded=false;
    private boolean markWillBeRemoved=false;

    public node(int x, nodeShape _shape) {
        nID = x;
        nKolor = 0;
        shape = _shape;
    }

    public node(int id, float iks, float ygr, nodeShape _shape) {
        nID = id;
        x = iks;
        y = ygr;
        nKolor = 0;
        shape = _shape;
    }
    
    public int getID() {
        return nID;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float iks) {
        x = iks;
    }
        public void setY(float iks) {
        y = iks;
    }

    public void setColor(float ygrek) {
        y = ygrek;
    }

    public void setColor(int color) {
        nKolor = color;
    }

    public int getColor() {
        return (nKolor);
    }
    
    public nodeShape getShape()
    {
        return shape;
    }

    public void planAddingMark()
    {
        markWillBeAdded = true;
    }
    public void planRemovingMark()
    {
        markWillBeRemoved = true;
    }
    
    private void resetPlanningMarks()
    {
        markWillBeAdded = false;
        markWillBeRemoved = false;
    }
    
    public void applyMarkChanges()
    {
        if(markWillBeAdded) addMark();
        if(markWillBeRemoved) removeMark();
        resetPlanningMarks();
    }
    
    public void addMark()
    {
        markCount++;
    }
    public void removeMark()
    {
        if(markCount>0) markCount--;
    }
    
    public boolean hasMark()
    {
        if(markCount>0) return true;
        return false;
    }
    public int getMarkCount()
    {
        return markCount;
    }
    
    public boolean isTransition()
    {
        return this.shape==nodeShape.RECTANGLE;
    }
    
    @Override
    public String toString() {
        return (Integer.toString(this.getID()));
    }
}
