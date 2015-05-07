/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetProperties;

import model.node;
import smip.SMiP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NetProperties {
    public boolean netIsAlive = true;
    public boolean checkIfNetAlive()
    {
        Collection<node> allNodes = SMiP.graphNet.getVertices();
        boolean transitionIsActive;
        for(node actualNode:allNodes)
        {
            if(actualNode.isTransition())
            {
                transitionIsActive = true;
                Collection<node> previousNodes = SMiP.graphNet.getPredecessors(actualNode); 
                for(node previousNode:previousNodes)
                {
                    transitionIsActive&=previousNode.hasMark();
                }
                if(!transitionIsActive) {
                    netIsAlive = false;
                    actualNode.setColor(10);
                }
                else actualNode.setColor(0);
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

        for (node node : nodes) {
            if (node.isTransition()) {
                transitions.add(node);
            } else {
                places.add(node);
            }
        }

        inputsMatrix = new int[transitions.size()][places.size()];
        outputsMatrix = new int[transitions.size()][places.size()];
        incidenceMatrix = new int[transitions.size()][places.size()];

        for (int i = 0; i < transitions.size(); i++) {
            node transition = transitions.get(i);

            for (int j = 0; j < places.size(); j++) {
                node place = places.get(j);

                if (SMiP.graphNet.isPredecessor(transition, place)) {
                    inputsMatrix[i][j] = 1;
                } else {
                    inputsMatrix[i][j] = 0;
                }

                if (SMiP.graphNet.isSuccessor(transition, place)) {
                    outputsMatrix[i][j] = 1;
                } else {
                    outputsMatrix[i][j] = 0;
                }

                incidenceMatrix[i][j] = inputsMatrix[i][j] - outputsMatrix[i][j];
            }
        }

        return incidenceMatrix;
    }
    public void resetProperties()
    {
        Collection<node> allNodes = SMiP.graphNet.getVertices();
        for(node actualNode:allNodes)
        {
            if(actualNode.isTransition())
            {
                actualNode.setColor(0);
            }
        }
    }
    
    
    
}
