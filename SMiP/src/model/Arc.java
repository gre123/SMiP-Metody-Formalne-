package model;

import java.io.Serializable;

public class Arc implements Serializable {

    private static final long serialVersionUID = -6824530546630338833L;
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

}
