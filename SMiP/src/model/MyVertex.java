package model;

import java.io.Serializable;

/**
 *
 * @author Elpidiusz
 */
public class MyVertex implements Comparable<MyVertex>, Serializable {

    int id;
    String description;
    private int x;
    private int y;

    public MyVertex(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public MyVertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(MyVertex v2) {
        return ((Integer) this.id).compareTo((Integer) v2.id);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        MyVertex guest = (MyVertex) other;
        return (this.id == guest.id);
    }
}
