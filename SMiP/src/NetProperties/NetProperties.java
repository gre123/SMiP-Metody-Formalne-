package NetProperties;

import model.node;
import smip.SMiP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NetProperties {
    public boolean netIsAlive = true;

    public boolean checkIfNetAlive() {
        Collection<node> allNodes = SMiP.graphNet.getVertices();
        boolean transitionIsActive;
        for (node actualNode : allNodes) {
            if (actualNode.isTransition()) {
                transitionIsActive = true;
                Collection<node> previousNodes = SMiP.graphNet.getPredecessors(actualNode);
                for (node previousNode : previousNodes) {
                    transitionIsActive &= previousNode.hasMark();
                }
                if (!transitionIsActive) {
                    netIsAlive = false;
                    actualNode.setColor(10);
                } else actualNode.setColor(0);
            }
        }
        return netIsAlive;
    }

    public int[][] getIncidenceMatrix() {
        Collection<node> nodes = SMiP.graphNet.getVertices();
        List<node> transitions = new ArrayList<>();
        List<node> places = new ArrayList<>();
        int[][] inputsMatrix;
        int[][] outputsMatrix;
        int[][] incidenceMatrix;

        //tworzenie list miejsc i przejsc
        for (node node : nodes) {
            if (node.isTransition()) {
                transitions.add(node);
            } else {
                places.add(node);
            }
        }

        //inicjalizacja macierzy
        inputsMatrix = new int[places.size()][transitions.size()];
        outputsMatrix = new int[places.size()][transitions.size()];
        incidenceMatrix = new int[places.size() + 1][transitions.size() + 1];

        //tworzenie macierzy
        for (int i = 0; i < places.size(); i++) {
            node place = places.get(i);

            for (int j = 0; j < transitions.size(); j++) {
                node transition = transitions.get(j);

                if (SMiP.graphNet.isPredecessor(place, transition)) {
                    inputsMatrix[i][j] = 1;
                } else {
                    inputsMatrix[i][j] = 0;
                }

                if (SMiP.graphNet.isSuccessor(place, transition)) {
                    outputsMatrix[i][j] = 1;
                } else {
                    outputsMatrix[i][j] = 0;
                }

                if (j == 0) {
                    incidenceMatrix[i + 1][0] = place.getID();
                }
                if (i == 0) {
                    incidenceMatrix[0][j + 1] = transition.getID();
                }

                incidenceMatrix[i + 1][j + 1] = inputsMatrix[i][j] - outputsMatrix[i][j];
            }
        }

        return incidenceMatrix;
    }

    public void resetProperties() {
        Collection<node> allNodes = SMiP.graphNet.getVertices();
        for (node actualNode : allNodes) {
            if (actualNode.isTransition()) {
                actualNode.setColor(0);
            }
        }
    }


}
