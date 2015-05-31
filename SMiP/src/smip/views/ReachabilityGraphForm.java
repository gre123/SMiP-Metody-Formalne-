package smip.views;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import model.*;
import painter.ReachabilityGraphVertexColorPainter;
import painter.ReachabilityGraphVertexShapePainter;

import smip.rechabilityGraph.ReachabilityGraphCalculator;

/**
 * @author Grzesiek
 */
public class ReachabilityGraphForm extends javax.swing.JFrame {

    ReachabilityGraph reachabilityGraph;
    VisualizationViewer vv;

    ReachabilityGraphCalculator reachabilityGraphCalculator;
    /**
     * Creates new form ReachabilityGraphForm
     */
    public ReachabilityGraphForm() {
        initComponents();
        reachabilityGraph = new ReachabilityGraph();
        reachabilityGraphCalculator=new ReachabilityGraphCalculator(reachabilityGraph);
                
        jPanel1.setSize(600, 400);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        Layout<ReachabilityVertex, ReachabilityArc> layout = new KKLayout<>(reachabilityGraph);
        layout.setSize(this.jPanel1.getSize());

        vv = new VisualizationViewer<>(layout);
        vv.setPreferredSize(this.jPanel1.getSize());
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setLabelOffset(20);
        vv.getRenderContext().setVertexFillPaintTransformer(new ReachabilityGraphVertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new ReachabilityGraphVertexShapePainter());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        vv.setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.add(vv);
        jPanel1.validate();
        jPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlResult = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblVerticles = new javax.swing.JLabel();
        lblEdges = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblLive = new javax.swing.JLabel();
        lblZach = new javax.swing.JLabel();
        lblSafty = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        pnlResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Wierzchołki:");

        jLabel2.setText("Krawędzie:");

        lblVerticles.setText("0");

        lblEdges.setText("0");

        jLabel3.setText("Żywotnośc:");

        jLabel4.setText("Zachowawczość:");

        jLabel5.setText("Bezpieczeństwo");

        lblLive.setText("-");

        lblZach.setText("-");

        lblSafty.setText("-");

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblVerticles))
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEdges)))
                .addGap(43, 43, 43)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
                        .addComponent(lblLive)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblSafty))
                    .addGroup(pnlResultLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lblZach)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblVerticles)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(lblLive)
                    .addComponent(lblSafty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblEdges)
                    .addComponent(jLabel4)
                    .addComponent(lblZach))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReachabilityGraphForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReachabilityGraphForm().setVisible(true);
            }
        });
    }

    public void calculateReachabilityGraph(PetriGraph graph) {
         vv.repaint();
        reachabilityGraphCalculator.calculateAll(graph); 
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEdges;
    private javax.swing.JLabel lblLive;
    private javax.swing.JLabel lblSafty;
    private javax.swing.JLabel lblVerticles;
    private javax.swing.JLabel lblZach;
    private javax.swing.JPanel pnlResult;
    // End of variables declaration//GEN-END:variables
}
