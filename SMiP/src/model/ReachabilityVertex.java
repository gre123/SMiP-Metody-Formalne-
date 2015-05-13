package model;


public class ReachabilityVertex {

    int[] markers;

    public ReachabilityVertex(int[] markers) {
        this.markers = markers;
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
}
