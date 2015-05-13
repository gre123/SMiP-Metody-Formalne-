package model;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReachabilityVertex {

    int[] markers;
    Set<Transition> activeTransitions = new HashSet<>();

    public ReachabilityVertex(int[] markers) {
        this.markers = markers;
    }

    public void addActiveTransition(Transition transition) {
        activeTransitions.add(transition);
    }

    public Set<Transition> getActiveTransitions() {
        return activeTransitions;
    }

    public int getMarker(int i) {
        return markers[i];
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < markers.length; i++) {
            string += Integer.toString(markers[i]);
            if (i < markers.length - 1) {
                string += ", ";
            }
        }
        return "( " + string + " )";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ReachabilityVertex && Arrays.equals(markers, ((ReachabilityVertex) obj).markers);
    }

    @Override
    public int hashCode() {
        return markers.length;
    }
}
