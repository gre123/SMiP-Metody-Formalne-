
package simulation;

import trash.SMiP;

public class RunnableSimulation implements Runnable{
    
    @Override
    public void run() {
       while(true){
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {}
           SimulationUtil.next();
           SMiP.viewer.repaint();           
       }
    }    
}
