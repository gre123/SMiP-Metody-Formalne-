package model;

/**
 * @author Elpidiusz
 */
public class Transition extends MyVertex {

    boolean active;

    public Transition(int id) {
        super(id, "Transition");
        this.id = id;
    }

    public String toString() {
        return "T" + id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean Active) {
        this.active = Active;
    }

}
