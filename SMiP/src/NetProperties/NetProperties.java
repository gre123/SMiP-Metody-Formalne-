/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetProperties;

import java.util.Collection;
import model.node;
import smip.SMiP;

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
