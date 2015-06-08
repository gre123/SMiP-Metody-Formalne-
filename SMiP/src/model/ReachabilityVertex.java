package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReachabilityVertex {

    int[] markers;
    Set<Transition> activeTransitions = new HashSet<>(200);

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

    public int[] getMarkers() {
        return markers;
    }

    @Override
    public String toString() {
        String string = "(";
        for (int i = 0; i < markers.length-1; i++) {
            string += Integer.toString(markers[i])+",";
        }
        if(markers.length-1>=0){
            string += Integer.toString(markers[markers.length-1]);
        }
        return  string + " )";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ReachabilityVertex && Arrays.equals(markers, ((ReachabilityVertex) obj).markers);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Arrays.hashCode(this.markers);
        return hash;
    }
    

    

    
}
