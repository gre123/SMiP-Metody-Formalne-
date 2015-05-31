
package simulation;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.util.List;
import java.util.Random;
import model.PetriGraph;
import model.Transition;

public class RunnableSimulationPetriGraph implements Runnable{
    PetriGraph graph;
    VisualizationViewer vv;
    int delay=1000;
    int transitionPerStep=1;
    
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

    public int getTransitionPerStep() {
        return transitionPerStep;
    }

    public void setTransitionPerStep(int transitionPerStep) {
        this.transitionPerStep = transitionPerStep;
    }

    
    @Override
    public void run() {
       Random rn = new Random();
       int currentStep=0;
       while(currentStep<=transitionPerStep || transitionPerStep==-1){
           try {
               Thread.sleep(delay);
           } catch (InterruptedException ex) {
               return;
           }
           List<Transition> transitions = graph.getActiveTransitions();
           if (!transitions.isEmpty()){
               graph.executeTransition(transitions.get(rn.nextInt(transitions.size())));
               vv.repaint();
           }
          if(transitionPerStep>=0){
              currentStep++;
              System.out.println(currentStep);
          } 
          
       }
    }

    
    
}
