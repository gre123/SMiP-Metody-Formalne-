/*
 * W projekcie wykorzystana została biblioteka JUNG - http://jung.sourceforge.net/,
 * która jest open-source na licencji BSD
 * 
 */
package smip;

import MousePlugin.MarkGraphMousePlugin;
import MousePlugin.PopupGraphMousePlugin;
import factory.EdgeFactory;
import Struktura_jung.Przerob;
import factory.CircVertexFactory;
import Struktura_jung.lokator;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import java.awt.Color;
import javax.swing.JFrame;
import simulation.RunnableSimulation;

public class MainGUI extends javax.swing.JFrame{

   DefaultModalGraphMouse graphMouse;  
   PluggableGraphMouse myszka = new PluggableGraphMouse();
   public JFrame graphFrame;
   public Thread simulationThread;

    public MainGUI() {
        initComponents(); 
        graphMouse = new DefaultModalGraphMouse();
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        SMiP.graphNet = Przerob.getGraph();
        graphFrame=new JFrame();
        graphFrame.setBounds(190,255,735,485);
        graphFrame.setAlwaysOnTop(true);
        SMiP.viewer=Przerob.getViewer(SMiP.graphNet, "kk",graphFrame.getSize().width , graphFrame.getSize().height-25);
        graphFrame.add(SMiP.viewer);
        graphFrame.setTitle("Widok sieci");
        graphFrame.setForeground(Color.LIGHT_GRAY);
        graphFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        graphFrame.setVisible(true);
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        odMyszy = new javax.swing.JComboBox();
        czyszczenie = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        odLayoutu = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        startStopButton = new javax.swing.JToggleButton();
        odOprojekcie = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kolorowanie wierzchołków G.Bylina, T.Gajda, E.Wszołek 2013");
        setBackground(new java.awt.Color(147, 150, 171));
        setFocusCycleRoot(false);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 32, 153), 5), "Do grafu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(110, 20, 215))); // NOI18N

        odMyszy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transforming", "Picking", "Editing", "Znakowanie" }));
        odMyszy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 38, 147), 2));
        odMyszy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                odMyszyItemStateChanged(evt);
            }
        });
        odMyszy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odMyszyActionPerformed(evt);
            }
        });
        jLayeredPane2.add(odMyszy);
        odMyszy.setBounds(20, 40, 139, 24);

        czyszczenie.setBackground(new java.awt.Color(239, 208, 61));
        czyszczenie.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        czyszczenie.setForeground(new java.awt.Color(148, 146, 50));
        czyszczenie.setText("Wyczyść");
        czyszczenie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                czyszczenieActionPerformed(evt);
            }
        });
        jLayeredPane2.add(czyszczenie);
        czyszczenie.setBounds(20, 400, 140, 27);
        jLayeredPane2.add(jSeparator7);
        jSeparator7.setBounds(10, 120, 157, 14);

        odLayoutu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ISOM", "KK", "Circle", "Spring", "FR", "static(dla planarnych)" }));
        odLayoutu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 38, 147), 2));
        odLayoutu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        odLayoutu.setPreferredSize(new java.awt.Dimension(90, 30));
        odLayoutu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                odLayoutuItemStateChanged(evt);
            }
        });
        odLayoutu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odLayoutuActionPerformed(evt);
            }
        });
        jLayeredPane2.add(odLayoutu);
        odLayoutu.setBounds(20, 80, 139, 24);

        jLabel2.setText("Layout grafu:");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(20, 64, 140, 20);

        jLabel3.setText("Tryb myszy grafowej:");
        jLayeredPane2.add(jLabel3);
        jLabel3.setBounds(20, 26, 140, 14);

        jLayeredPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 0, 239), 5), "Do algorytmów", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(110, 20, 215))); // NOI18N

        startStopButton.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        startStopButton.setText("START");
        startStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(startStopButton);
        startStopButton.setBounds(20, 490, 140, 60);

        odOprojekcie.setBackground(new java.awt.Color(177, 165, 108));
        odOprojekcie.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        odOprojekcie.setForeground(new java.awt.Color(1, 1, 1));
        odOprojekcie.setText("O projekcie");
        odOprojekcie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odOprojekcieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 717, Short.MAX_VALUE)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(odOprojekcie, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(odOprojekcie, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2.getAccessibleContext().setAccessibleName("Sieć");
        jLayeredPane2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void odOprojekcieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odOprojekcieActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_odOprojekcieActionPerformed

    private void odLayoutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odLayoutuActionPerformed
        if (odLayoutu.getSelectedIndex()==0) {
            SMiP.viewer.setGraphLayout(new ISOMLayout(SMiP.graphNet));
            SMiP.viewer.repaint();
        } else if (odLayoutu.getSelectedIndex()==1) {
            SMiP.viewer.setGraphLayout(new KKLayout(SMiP.graphNet));
            SMiP.viewer.repaint();
        } else if (odLayoutu.getSelectedIndex()==2) {
            SMiP.viewer.setGraphLayout(new CircleLayout(SMiP.graphNet));
            SMiP.viewer.repaint();
        } else if (odLayoutu.getSelectedIndex()==3) {
            SMiP.viewer.setGraphLayout(new SpringLayout(SMiP.graphNet));
            SMiP.viewer.repaint();
        } else if (odLayoutu.getSelectedIndex()==4) {
            SMiP.viewer.setGraphLayout(new FRLayout(SMiP.graphNet));
            SMiP.viewer.repaint();
        } else if (odLayoutu.getSelectedIndex()==5) {
            SMiP.viewer.setGraphLayout(new StaticLayout<>(SMiP.graphNet, new lokator()));
            SMiP.viewer.repaint();
        }
    }//GEN-LAST:event_odLayoutuActionPerformed

    private void odLayoutuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_odLayoutuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_odLayoutuItemStateChanged

    private void czyszczenieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_czyszczenieActionPerformed

        EdgeFactory.zeruj();
        CircVertexFactory.zeruj();
        odMyszy.setSelectedIndex(0);
        odLayoutu.setSelectedIndex(0);
        SMiP.graphNet = Przerob.getGraph();
        graphFrame.dispose();
        graphFrame=new JFrame();
        graphFrame.setBounds(190,255,735,485);
        graphFrame.setTitle("Widok na graf");
        graphFrame.setAlwaysOnTop(true);
        SMiP.viewer=Przerob.getViewer(SMiP.graphNet, "kk",720,485-25);
        graphFrame.add(SMiP.viewer);
        graphFrame.setVisible(true);
    }//GEN-LAST:event_czyszczenieActionPerformed

    private void odMyszyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odMyszyActionPerformed


        if (SMiP.viewer.getModel().getRelaxer()!=null)
        {
            SMiP.viewer.getModel().getRelaxer().stop();
        }
        myszka = new PluggableGraphMouse();
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        switch((String)odMyszy.getSelectedItem())
        {
            case "Transforming":
            {    
                SMiP.viewer.setGraphMouse(graphMouse);
                SMiP.viewer.repaint();
                break;

            }
            case "Picking":
            {
                graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
                SMiP.viewer.setGraphMouse(graphMouse);
                SMiP.viewer.repaint();
                break;
            }
            case "Editing":
            {
                myszka.add(new PopupGraphMousePlugin());
                /**
                 * To miejsce wlasnie generuje wyjateczki przy kliknieciu w trybie Editing - Grzesiek
                 * TO DO poprawic
                 */
                myszka.add(new EditingGraphMousePlugin(null , new EdgeFactory()));

                SMiP.viewer.setGraphMouse(myszka);
                SMiP.viewer.repaint();
                break;
            }
            case "Znakowanie":
            {
                myszka.add(new MarkGraphMousePlugin());
                SMiP.viewer.setGraphMouse(myszka);
                SMiP.viewer.repaint();
                break;
            }
        }
    }//GEN-LAST:event_odMyszyActionPerformed

    private void odMyszyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_odMyszyItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_odMyszyItemStateChanged

    private void startStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopButtonActionPerformed
        if(startStopButton.isSelected())
        {
            simulationThread = new Thread(new RunnableSimulation());
            simulationThread.start();
        }
        else
        {
            simulationThread.stop();
        }
        

// TODO add your handling code here:
    }//GEN-LAST:event_startStopButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton czyszczenie;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JComboBox odLayoutu;
    private javax.swing.JComboBox odMyszy;
    private javax.swing.JButton odOprojekcie;
    private javax.swing.JToggleButton startStopButton;
    // End of variables declaration//GEN-END:variables

    
}
