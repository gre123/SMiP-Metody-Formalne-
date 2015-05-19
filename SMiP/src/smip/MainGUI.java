/*
 * W projekcie wykorzystana została biblioteka JUNG - http://jung.sourceforge.net/,
 * która jest open-source na licencji BSD
 * 
 */
package smip;

import trash.SMiP;
import MousePlugin.MarkGraphMousePlugin;
import MousePlugin.PopupGraphMousePlugin;
import NetProperties.NetProperties;
import Struktura_jung.Przerob;
import Struktura_jung.lokator;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import factory.CircVertexFactory;
import factory.EdgeFactory;
import simulation.RunnableSimulation;

import javax.swing.*;
import java.awt.*;
import smip.table.Table;

public class MainGUI extends javax.swing.JFrame {

    DefaultModalGraphMouse graphMouse;
    PluggableGraphMouse myszka = new PluggableGraphMouse();
    public JFrame graphFrame;
    public Thread simulationThread;
    public NetProperties netProperties;
    public RaportConsole raportConsole;

    public MainGUI() {
        initComponents();
        netProperties = new NetProperties();
        raportConsole = new RaportConsole(raportArea);
        graphMouse = new DefaultModalGraphMouse();
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        SMiP.graphNet = Przerob.getGraph();
        SMiP.viewer = Przerob.getViewer(SMiP.graphNet, "kk", pnlMain.getSize().width, pnlMain.getSize().getSize().height);
        pnlMain.add(SMiP.viewer);
        pnlMain.validate();
        pnlMain.repaint();

//        graphFrame=new JFrame();
//        graphFrame.setBounds(190,255,735,485);
//        graphFrame.setAlwaysOnTop(true);
//        SMiP.viewer=Przerob.getViewer(SMiP.graphNet, "kk",graphFrame.getSize().width , graphFrame.getSize().height-25);
//        graphFrame.add(SMiP.viewer);
//        graphFrame.setTitle("Widok sieci");
//        graphFrame.setForeground(Color.LIGHT_GRAY);
//        graphFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
//        graphFrame.setVisible(true);
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
        checkAlive = new javax.swing.JToggleButton();
        btnMatrixInc = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        raportArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sieci miejsc i przejść");
        setBackground(new java.awt.Color(147, 150, 171));
        setFocusCycleRoot(false);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 5), "Sieć", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(102, 153, 0))); // NOI18N

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
        czyszczenie.setBounds(20, 507, 140, 30);
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

        jLabel2.setText("Layout sieci:");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(20, 64, 140, 20);

        jLabel3.setText("Tryb myszy grafowej:");
        jLayeredPane2.add(jLabel3);
        jLabel3.setBounds(20, 26, 140, 14);

        jLayeredPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 5), "Właściwości sieci", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(51, 153, 0))); // NOI18N

        startStopButton.setBackground(new java.awt.Color(51, 102, 0));
        startStopButton.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        startStopButton.setForeground(new java.awt.Color(153, 153, 0));
        startStopButton.setText("START");
        startStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(startStopButton);
        startStopButton.setBounds(20, 510, 140, 30);

        checkAlive.setText("Sprawdź żywotność");
        checkAlive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAliveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(checkAlive);
        checkAlive.setBounds(10, 50, 160, 23);

        btnMatrixInc.setText("Macierz incydencji");
        btnMatrixInc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatrixIncActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btnMatrixInc);
        btnMatrixInc.setBounds(10, 80, 160, 23);

        raportArea.setEditable(false);
        raportArea.setColumns(20);
        raportArea.setRows(5);
        jScrollPane1.setViewportView(raportArea);

        jTabbedPane1.addTab("Raport", jScrollPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Macierz incidencji", jPanel2);

        jMenu1.setText("Sieć");

        jMenuItem2.setText("Zapisz sieć");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Wczytaj sieć");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("O programie");

        jMenuItem3.setText("Autorzy");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Licencja");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2.getAccessibleContext().setAccessibleDescription("");
        jLayeredPane1.getAccessibleContext().setAccessibleName("Symulacja");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void odLayoutuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odLayoutuActionPerformed
        switch (odLayoutu.getSelectedIndex()) {
            case 0:
                SMiP.viewer.setGraphLayout(new ISOMLayout(SMiP.graphNet));
                SMiP.viewer.repaint();
            case 1:
                SMiP.viewer.setGraphLayout(new KKLayout(SMiP.graphNet));
                SMiP.viewer.repaint();
            case 2:
                SMiP.viewer.setGraphLayout(new CircleLayout(SMiP.graphNet));
                SMiP.viewer.repaint();
            case 3:
                SMiP.viewer.setGraphLayout(new SpringLayout(SMiP.graphNet));
                SMiP.viewer.repaint();
            case 4:
                SMiP.viewer.setGraphLayout(new FRLayout(SMiP.graphNet));
                SMiP.viewer.repaint();
            case 5:
                SMiP.viewer.setGraphLayout(new StaticLayout<>(SMiP.graphNet, new lokator()));
                SMiP.viewer.repaint();
        }
    }//GEN-LAST:event_odLayoutuActionPerformed

    private void odLayoutuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_odLayoutuItemStateChanged

    }//GEN-LAST:event_odLayoutuItemStateChanged

    private void czyszczenieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_czyszczenieActionPerformed
        EdgeFactory.zeruj();
        CircVertexFactory.zeruj();
        odMyszy.setSelectedIndex(0);
        odLayoutu.setSelectedIndex(0);
        SMiP.graphNet = Przerob.getGraph();
        graphFrame.dispose();
        graphFrame = new JFrame();
        graphFrame.setBounds(190, 255, 735, 485);
        graphFrame.setTitle("Widok na graf");
        graphFrame.setAlwaysOnTop(true);
        SMiP.viewer = Przerob.getViewer(SMiP.graphNet, "kk", 720, 485 - 25);
        graphFrame.add(SMiP.viewer);
        graphFrame.setVisible(true);
    }//GEN-LAST:event_czyszczenieActionPerformed

    private void odMyszyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odMyszyActionPerformed

        if (SMiP.viewer.getModel().getRelaxer() != null) {
            SMiP.viewer.getModel().getRelaxer().stop();
        }
        myszka = new PluggableGraphMouse();
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        switch ((String) odMyszy.getSelectedItem()) {
            case "Transforming":
                SMiP.viewer.setGraphMouse(graphMouse);
                SMiP.viewer.repaint();
                break;
            case "Picking":
                graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
                SMiP.viewer.setGraphMouse(graphMouse);
                SMiP.viewer.repaint();
                break;
            case "Editing":
                myszka.add(new PopupGraphMousePlugin());
                /**
                 * To miejsce wlasnie generuje wyjateczki przy kliknieciu w
                 * trybie Editing - Grzesiek TO DO poprawic
                 */
                myszka.add(new EditingGraphMousePlugin(null, new EdgeFactory()));

                SMiP.viewer.setGraphMouse(myszka);
                SMiP.viewer.repaint();
                break;
            case "Znakowanie":
                myszka.add(new MarkGraphMousePlugin());
                SMiP.viewer.setGraphMouse(myszka);
                SMiP.viewer.repaint();
                break;
        }
    }//GEN-LAST:event_odMyszyActionPerformed

    private void odMyszyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_odMyszyItemStateChanged

    }//GEN-LAST:event_odMyszyItemStateChanged

    private void startStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopButtonActionPerformed
        if (startStopButton.isSelected()) {
            simulationThread = new Thread(new RunnableSimulation());
            simulationThread.start();
        } else {
            simulationThread.stop();
        }
    }//GEN-LAST:event_startStopButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new SaveLoadGui('l').setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new SaveLoadGui('s').setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        JDialog authors = new JDialog(this, "Autorzy");
        authors.setSize(200, 300);
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

    private void checkAliveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAliveActionPerformed
        if (checkAlive.isSelected()) {
            raportConsole.printLine(" Żywotność sieci : " + netProperties.checkIfNetAlive());
        } else {
            netProperties.resetProperties();
            SMiP.viewer.repaint();
        }
    }//GEN-LAST:event_checkAliveActionPerformed

    private void btnMatrixIncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatrixIncActionPerformed
        raportConsole.printMatrix(netProperties.getIncidenceMatrix());
    }//GEN-LAST:event_btnMatrixIncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMatrixInc;
    private javax.swing.JToggleButton checkAlive;
    private javax.swing.JButton czyszczenie;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox odLayoutu;
    private javax.swing.JComboBox odMyszy;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextArea raportArea;
    private javax.swing.JToggleButton startStopButton;
    // End of variables declaration//GEN-END:variables

}
