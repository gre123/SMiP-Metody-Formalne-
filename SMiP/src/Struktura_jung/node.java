/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Struktura_jung;

import model.nodeShape;

/**
 * Klasa reprezentująca wierzchołek. Ma kolor i niezmienialne po utworzeniu ID
 * (pasowałoby niepowtarzalne).
 *
 * @author Epifaniusz
 */


public class node {

    private final int nID;
    private float x;
    private float y;
    private int nKolor;
    private nodeShape shape;
    private int markCount=0;

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

    public void addMark()
    {
        markCount++;
    }
    
    public int getMarkCount()
    {
        return markCount;
    }
    
    @Override
    public String toString() {
        return (Integer.toString(this.getID()) + " kolor= " + Integer.toString(this.getColor()));
    }
}
