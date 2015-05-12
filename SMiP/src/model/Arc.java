package model;

/**
 * @author Elpidiusz
 */
public class Arc {

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
        if (value > 0) {
            value--;
            return true;
        }
        return false;
    }

    public void setValue(int value) {
        if (value >= 0) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("nie można ustawić ujemnej wagi");
        }
    }

}
