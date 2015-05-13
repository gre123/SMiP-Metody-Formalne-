package model;

/**
 *
 * @author Elpidiusz
 */
public class MyVertex implements Comparable<MyVertex> {

    int id;
    String description;

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

    @Override
    public int compareTo(MyVertex v2) {
        return ((Integer) this.id).compareTo((Integer) v2.id);
    }
}
