/*
 */
package smip;

import CheckingMouse.ArcChecker;
import CheckingMouse.EditingCheckingGraphMousePlugin;
import CheckingMouse.EditingModalGraphMouse2;
import CheckingMouse.MyVertexChecker;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import factory.ArcFactory;
import factory.PlaceTransitionFactory;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import model.Arc;
import model.MyVertex;
import model.PetriGraph;
import org.apache.commons.collections15.Factory;
import painter.MyVertexColorPainter;
import painter.MyVertexShapePainter;
import simulation.RunnableSimulationPetriGraph;

/**
 *
 * @author Elpidiusz
 */
public class PetriGraphGUI extends javax.swing.JFrame {
    public PetriGraph graph;
    Factory <MyVertex> vertexFactory;
    Factory <Arc> edgeFactory;
    MyVertexChecker vCheck;
    ArcChecker eCheck;
    VisualizationViewer vv;
    Thread simulationThread;


    /**
     * Creates new form PetriGraphGUI
     */
    public PetriGraphGUI() {
        initComponents();
        graph = new PetriGraph();
        vertexFactory = new PlaceTransitionFactory();
        edgeFactory = new ArcFactory();
        vCheck = new MyVertexChecker();
        eCheck = new ArcChecker();
        
        Layout<MyVertex, Arc> layout = new StaticLayout(graph);
        layout.setSize(this.jPanelGraph.getSize());
        vv = new VisualizationViewer<MyVertex, Arc>(layout);
        vv.setPreferredSize(this.jPanelGraph.getSize());
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexFillPaintTransformer(new MyVertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new MyVertexShapePainter());
        // Create a graph mouse and add it to the visualization viewer
        EditingModalGraphMouse2 gm = new EditingModalGraphMouse2(vv.getRenderContext(), 
                 vertexFactory, edgeFactory); 
        EditingCheckingGraphMousePlugin plugin = new EditingCheckingGraphMousePlugin(vertexFactory,
                edgeFactory);
        GraphMousePlugin oldPlugin = gm.getEditingPlugin(); //Remove current plugin
        gm.remove(oldPlugin);
        plugin.setVertexChecker(vCheck);
        plugin.setEdgeChecker(eCheck);
        gm.setEditingPlugin(plugin);
        vv.setGraphMouse(gm);

        // Let's add a menu for changing mouse modes
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null);
        gm.setMode(ModalGraphMouse.Mode.EDITING);
        modeMenu.setPreferredSize(new Dimension(80,20));
        
        simulationThread=new Thread(new RunnableSimulationPetriGraph(graph, vv));

        
        jMenuBar.add(modeMenu);
        
        
        jPanelGraph.add(vv);
        jPanelGraph.validate();
        jPanelGraph.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelActions = new javax.swing.JPanel();
        jButtonActive = new javax.swing.JButton();
        jButtonNplus = new javax.swing.JButton();
        jButtonNminus = new javax.swing.JButton();
        jButtonIncidenceMatrix = new javax.swing.JButton();
        jToggleButtonSymulacja = new javax.swing.JToggleButton();
        jPanelGraph = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sieć miejsc i przejsć");
        setName("main_frame"); // NOI18N

        jPanelActions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelActions.setToolTipText("Panel z akcjami do wykonania na grafie");
        jPanelActions.setPreferredSize(new java.awt.Dimension(112, 400));

        jButtonActive.setText("Aktywność");
        jButtonActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActiveActionPerformed(evt);
            }
        });

        jButtonNplus.setText("Macierz wejść");
        jButtonNplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNplusActionPerformed(evt);
            }
        });

        jButtonNminus.setText("Macierz wyjść");
        jButtonNminus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNminusActionPerformed(evt);
            }
        });

        jButtonIncidenceMatrix.setText("Macierz incydecji");
        jButtonIncidenceMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncidenceMatrixActionPerformed(evt);
            }
        });

        jToggleButtonSymulacja.setText("Symuluj");
        jToggleButtonSymulacja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSymulacjaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionsLayout = new javax.swing.GroupLayout(jPanelActions);
        jPanelActions.setLayout(jPanelActionsLayout);
        jPanelActionsLayout.setHorizontalGroup(
            jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNplus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNminus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIncidenceMatrix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonSymulacja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelActionsLayout.setVerticalGroup(
            jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonActive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNplus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNminus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIncidenceMatrix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButtonSymulacja)
                .addContainerGap())
        );

        jPanelGraph.setToolTipText("Panel z grafem");
        jPanelGraph.setPreferredSize(new java.awt.Dimension(600, 400));

        jMenu1.setText("File");
        jMenuBar.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar.add(jMenu2);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelActions, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelActions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGraph, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActiveActionPerformed
        System.out.println("Wszystkie przejścia są aktywe: "+graph.updateGraphTransitionStates());
    }//GEN-LAST:event_jButtonActiveActionPerformed

    private void jButtonNplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNplusActionPerformed
        System.out.println("Macierz N+: "+java.util.Arrays.deepToString(graph.getNplus()));
    }//GEN-LAST:event_jButtonNplusActionPerformed

    private void jButtonNminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNminusActionPerformed
        System.out.println("Macierz N-: "+java.util.Arrays.deepToString(graph.getNminus()));
    }//GEN-LAST:event_jButtonNminusActionPerformed

    private void jButtonIncidenceMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncidenceMatrixActionPerformed
        System.out.println("Macierz incydencji: "+java.util.Arrays.deepToString(graph.getNincidence()));
    }//GEN-LAST:event_jButtonIncidenceMatrixActionPerformed

    private void jToggleButtonSymulacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSymulacjaActionPerformed
        if (jToggleButtonSymulacja.isSelected()) {
            simulationThread.start();
        } else{
            simulationThread.stop(); //tak się nie powinno robić, ale nie umiem tak jak się powinno
        }
    }//GEN-LAST:event_jToggleButtonSymulacjaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PetriGraphGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PetriGraphGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PetriGraphGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetriGraphGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetriGraphGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActive;
    private javax.swing.JButton jButtonIncidenceMatrix;
    private javax.swing.JButton jButtonNminus;
    private javax.swing.JButton jButtonNplus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JToggleButton jToggleButtonSymulacja;
    // End of variables declaration//GEN-END:variables
}
