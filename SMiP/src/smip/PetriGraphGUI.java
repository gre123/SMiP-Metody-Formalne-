package smip;

import CheckingMouse.ArcChecker;
import CheckingMouse.EditingCheckingGraphMousePlugin;
import CheckingMouse.EditingModalGraphMouse2;
import CheckingMouse.MyVertexChecker;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import factory.ArcFactory;
import factory.PlaceTransitionFactory;
import model.Arc;
import model.MyVertex;
import model.PetriGraph;
import org.apache.commons.collections15.Factory;
import painter.MyVertexColorPainter;
import painter.MyVertexShapePainter;
import simulation.RunnableSimulationPetriGraph;
import smip.views.MatrixForm;

import javax.swing.*;
import java.awt.*;

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
    MatrixForm matrixForm;
    RunnableSimulationPetriGraph simulationPetriGraph;
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
        
        jPanelGraph.setSize(600, 400);
        
        Layout<MyVertex, Arc> layout = new StaticLayout(graph);
        layout.setSize(this.jPanelGraph.getSize());
        
        vv = new VisualizationViewer<>(layout);
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

        gm.remove(gm.getEditingPlugin());

        plugin.setVertexChecker(vCheck);
        plugin.setEdgeChecker(eCheck);
        
        gm.setEditingPlugin(plugin);
        vv.setGraphMouse(gm);
        
        gm.setMode(ModalGraphMouse.Mode.EDITING);
        simulationPetriGraph=new RunnableSimulationPetriGraph(graph, vv);
        simulationThread=new Thread(simulationPetriGraph);
        vv.setBackground(new java.awt.Color(204, 255, 255));
        createMenu(gm);

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
        sldDeley = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        lblDelayVal = new javax.swing.JLabel();
        jPanelGraph = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        elmViews = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

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

        sldDeley.setMajorTickSpacing(200);
        sldDeley.setMaximum(1200);
        sldDeley.setMinimum(100);
        sldDeley.setMinorTickSpacing(100);
        sldDeley.setPaintTicks(true);
        sldDeley.setSnapToTicks(true);
        sldDeley.setValue(1000);
        sldDeley.setPreferredSize(new java.awt.Dimension(50, 23));
        sldDeley.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldDeleyStateChanged(evt);
            }
        });

        jLabel1.setText("Opóżnienie:");

        lblDelayVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDelayVal.setText("1000 ms");

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
                    .addComponent(jToggleButtonSymulacja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sldDeley, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelActionsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDelayVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDelayVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldDeley, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonSymulacja)
                .addContainerGap())
        );

        jPanelGraph.setToolTipText("Panel z grafem");
        jPanelGraph.setPreferredSize(new java.awt.Dimension(600, 400));

        jMenu3.setText("O programie");

        jMenuItem3.setText("Autorzy");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Licencja");
        jMenu3.add(jMenuItem4);

        menuBar.add(jMenu3);

        jMenu4.setText("Sieć");

        jMenuItem2.setText("Zapisz sieć");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem1.setText("Wczytaj sieć");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        menuBar.add(jMenu4);

        elmViews.setText("Widok");

        jMenuItem5.setText("Macierz");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        elmViews.add(jMenuItem5);

        menuBar.add(elmViews);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelActions, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createMenu(EditingModalGraphMouse2 gm){
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null);
        modeMenu.setPreferredSize(new Dimension(80,20));
        menuBar.add(modeMenu);
    }
    
    private void jButtonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActiveActionPerformed
        System.out.println("Wszystkie przejścia są aktywe: "+graph.updateGraphTransitionStates());
    }//GEN-LAST:event_jButtonActiveActionPerformed

    private void jButtonNplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNplusActionPerformed
        System.out.println("Macierz N+: "+java.util.Arrays.deepToString(graph.getNplus()));
        drawTable(graph.getNplus());
    }//GEN-LAST:event_jButtonNplusActionPerformed

    private void jButtonNminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNminusActionPerformed
        System.out.println("Macierz N-: "+java.util.Arrays.deepToString(graph.getNminus()));
         drawTable(graph.getNminus());
    }//GEN-LAST:event_jButtonNminusActionPerformed

    private void jButtonIncidenceMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncidenceMatrixActionPerformed
        System.out.println("Macierz incydencji: "+java.util.Arrays.deepToString(graph.getNincidence()));
        drawTable(graph.getNincidence());
    }//GEN-LAST:event_jButtonIncidenceMatrixActionPerformed

    private void drawTable(int[][] matrix){       
        if (matrixForm==null){
            matrixForm=new MatrixForm();
        }

        matrixForm.setVisible(true);
         if (matrix == null){return; }
        matrixForm.drawTable(matrix, graph.getTransitionSet(), graph.getPlaceSet());
    }
    
    private void jToggleButtonSymulacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSymulacjaActionPerformed
        if (jToggleButtonSymulacja.isSelected()) {
            simulationThread.start();
        } else{
            simulationThread.stop(); //tak się nie powinno robić, ale nie umiem tak jak się powinno
        }
    }//GEN-LAST:event_jToggleButtonSymulacjaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        JDialog authors = new JDialog(this, "Autorzy");
        authors.setSize(200, 100);
        JTextArea engineers = new JTextArea("pan inżynier Elpidiusz Wszołek \n"
            + "pan inżynier Tomasz Gajda\n"
            + "pan inżynier Piotr Knop\n"
            + "pan inżynier Grzegorz Bylina");
        engineers.setEditable(false);
        engineers.setBackground(Color.LIGHT_GRAY);
        authors.add(engineers);
        authors.setAlwaysOnTop(true);
        authors.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new SaveLoadGui('s').setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new SaveLoadGui('l').setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        drawTable(null);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void sldDeleyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldDeleyStateChanged
        lblDelayVal.setText(Integer.toString(sldDeley.getValue())+ " ms");
        simulationPetriGraph.setDelay(sldDeley.getValue());
    }//GEN-LAST:event_sldDeleyStateChanged

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
    private javax.swing.JMenu elmViews;
    private javax.swing.JButton jButtonActive;
    private javax.swing.JButton jButtonIncidenceMatrix;
    private javax.swing.JButton jButtonNminus;
    private javax.swing.JButton jButtonNplus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JToggleButton jToggleButtonSymulacja;
    private javax.swing.JLabel lblDelayVal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JSlider sldDeley;
    // End of variables declaration//GEN-END:variables
}
