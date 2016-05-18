package model;

import java.io.Serializable;

/**
 * @author Elpidiusz
 */
public class Arc implements Serializable{

    int value = 1;

    public Arc() {
        super();
    }

    public Arc(int val) {
        value = val;
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }

    public int getValue() {
        return value;
    }

    public void incValue() {
        value++;
    }

    public boolean decValue() {
        if (value > 1) {
            value--;
            return true;
        }
        return false;
    }

    public void setValue(int value) {
        if (value >= 1) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("nie można ustawić ujemnej wagi");
        }
    }
	
	public void costam(){
	}

}
