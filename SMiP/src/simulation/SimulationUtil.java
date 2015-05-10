package simulation;

import java.util.Collection;
import model.node;
import smip.SMiP;

public class SimulationUtil {
    
    public static void next(){
        Collection<node> allNodes = SMiP.graphNet.getVertices();
        boolean transitionIsActive;
        for(node actualNode:allNodes){
            if(actualNode.isTransition())
            {
                transitionIsActive = true;
                Collection<node> previousNodes = SMiP.graphNet.getPredecessors(actualNode); 
                Collection<node> nextNodes = SMiP.graphNet.getSuccessors(actualNode);
                for(node previousNode:previousNodes){
                    transitionIsActive&=previousNode.hasMark();
                }
                if(transitionIsActive){
                    for(node previousNode:previousNodes){
                        previousNode.planRemovingMark();
                    }
                    for(node nextNode:nextNodes){
                        nextNode.planAddingMark();
                    }
                }
            }
        }
        applyChanges(allNodes);
    }
    
    private static void applyChanges(Collection<node> places)
    {
        for(node place:places){
            if(!place.isTransition()) place.applyMarkChanges();
        }
    }
}
