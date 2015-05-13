package model;


public class ReachabilityArc {
    int transitionId;

    public ReachabilityArc() {
        super();
    }

    public ReachabilityArc(int transitionId) {
        this.transitionId = transitionId;
    }

    @Override
    public String toString() {
        return "T" + transitionId;
    }
}
