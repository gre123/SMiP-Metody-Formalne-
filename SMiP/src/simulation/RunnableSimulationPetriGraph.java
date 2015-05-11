
package simulation;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.PetriGraph;
import model.Transition;
import smip.SMiP;

public class RunnableSimulationPetriGraph implements Runnable{
    PetriGraph graph;
    VisualizationViewer vv;
    int delay=1000;
    
    public RunnableSimulationPetriGraph(PetriGraph graph, VisualizationViewer vv){
        this.graph = graph;
        this.vv = vv;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    @Override
    public void run() {
       Random rn = new Random();
       while(true)
       {
           try {
               Thread.sleep(delay);
           } catch (InterruptedException ex) {}
           List<Transition> transitions = graph.getActiveTransitions();
           if (!transitions.isEmpty()){
               graph.executeTransition(transitions.get(rn.nextInt(transitions.size())));
               vv.repaint();
           }
       }
    }    
}
