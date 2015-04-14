/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Struktura_jung;

import java.util.LinkedList;

/**
 *
 * @author Epifaniusz
 */
public class Wiersz {
    public int kolor;
    public LinkedList<Integer> sasiedzi;
    public Wiersz(){
        sasiedzi = new LinkedList<>();
        kolor = 0;
    }
}
