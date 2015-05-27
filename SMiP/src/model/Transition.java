package model;

/**
 * @author Elpidiusz
 */
public class Transition extends MyVertex {

    boolean active;
    boolean l1alive;

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

    public boolean isL1alive() {
        return l1alive;
    }

    public void setL1alive(boolean l1alive) {
        this.l1alive = l1alive;
    }

}
