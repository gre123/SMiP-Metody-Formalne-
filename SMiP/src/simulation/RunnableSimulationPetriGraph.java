package simulation;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import model.PetriGraph;
import model.Transition;
import smip.Properties;

public class RunnableSimulationPetriGraph implements Runnable {

    PetriGraph graph;
    VisualizationViewer vv;
    int delay = 1000;
    int transitionPerStep = 1;
    JToggleButton button;
    JLabel lblTimeDiff;
    Properties properties;

    public RunnableSimulationPetriGraph(PetriGraph graph, VisualizationViewer vv) {
        this.graph = graph;
        this.vv = vv;
    }

    public VisualizationViewer getVv() {
        return vv;
    }

    public JLabel getLblTimeDiff() {
        return lblTimeDiff;
    }

    public void setLblTimeDiff(JLabel lblTimeDiff) {
        this.lblTimeDiff = lblTimeDiff;
    }

    public void setVv(VisualizationViewer vv) {
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

    public JToggleButton getButton() {
        return button;
    }

    public void setButton(JToggleButton button) {
        this.button = button;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void run() {
        Random rn = new Random();
        int currentStep = 0;

        while (currentStep <= transitionPerStep || transitionPerStep == -1) {
            long start = System.currentTimeMillis();
            List<Transition> transitions = graph.getActiveTransitions();
            if (!transitions.isEmpty()) {
                graph.executeTransition(transitions.get(rn.nextInt(transitions.size())));
                vv.repaint();
            }
            if (transitionPerStep >= 0) {
                currentStep++;
                System.out.println(currentStep);
            }
            properties.refreshProperties();
            
            long diff = System.currentTimeMillis() - start;
            lblTimeDiff.setText(diff+ " ms");
            long newDeley=delay-diff;
            if(newDeley<1){newDeley=1;}
            try {
                Thread.sleep(newDeley);
            } catch (InterruptedException ex) {
                return;
            }
        }
        button.setSelected(false);
    }

}
