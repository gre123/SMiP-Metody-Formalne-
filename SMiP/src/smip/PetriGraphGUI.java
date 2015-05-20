package smip;

import CheckingMouse.ArcChecker;
import CheckingMouse.EditingCheckingGraphMousePlugin;
import CheckingMouse.EditingModalGraphMouse2;
import CheckingMouse.MyVertexChecker;
import MousePlugin.SimulateGraphMousePlugin;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
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
import smip.views.ReachabilityGraphForm;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Map;
import model.Place;
import org.apache.commons.collections15.Transformer;
import smip.views.Showgraph;

/**
 *
 * @author Elpidiusz
 */
public class PetriGraphGUI extends javax.swing.JFrame {

    public static PetriGraph graph;
    Factory<MyVertex> vertexFactory;
    Factory<Arc> edgeFactory;
    MyVertexChecker vCheck;
    ArcChecker eCheck;
    VisualizationViewer vv;
    Thread simulationThread;
    MatrixForm matrixForm;
    ReachabilityGraphForm reachabilityGraphForm;
    RunnableSimulationPetriGraph simulationPetriGraph;
    EditingModalGraphMouse2 gm;
    
    public SimulateGraphMousePlugin simulateGraphMousePlugin = new SimulateGraphMousePlugin();
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
        vv.getRenderContext().setLabelOffset(20);
        vv.getRenderContext().setVertexFillPaintTransformer(new MyVertexColorPainter());
        vv.getRenderContext().setVertexShapeTransformer(new MyVertexShapePainter());
        // Create a graph mouse and add it to the visualization viewer
        
        EditingCheckingGraphMousePlugin plugin = new EditingCheckingGraphMousePlugin(vertexFactory,
                edgeFactory);
        
        gm = new EditingModalGraphMouse2(vv.getRenderContext(),vertexFactory, edgeFactory);
        gm.remove(gm.getEditingPlugin());

        plugin.setVertexChecker(vCheck);
        plugin.setEdgeChecker(eCheck);

        gm.setEditingPlugin(plugin);
        vv.setGraphMouse(gm);

        createMenu(gm);
        gm.setMode(ModalGraphMouse.Mode.EDITING);
        simulationPetriGraph = new RunnableSimulationPetriGraph(graph, vv);
        vv.setBackground(new java.awt.Color(204, 255, 255));

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
        jButtonReachabilityGraph = new javax.swing.JButton();
        jButtonCoverabilityGraph = new javax.swing.JButton();
        jButtonBoundedness = new javax.swing.JButton();
        jButtonConservation = new javax.swing.JButton();
        isSelectionByUser = new javax.swing.JCheckBox();
        jButtonReversibility = new javax.swing.JButton();
        jPanelGraph = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        elmViews = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        mitOsi = new javax.swing.JMenuItem();
        mitPokrycia = new javax.swing.JMenuItem();

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

        jLabel1.setText("Opóźnienie:");

        lblDelayVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDelayVal.setText("1000 ms");

        jButtonReachabilityGraph.setText("Graf osiągalności");
        jButtonReachabilityGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReachabilityGraphActionPerformed(evt);
            }
        });

        jButtonCoverabilityGraph.setText("Graf pokrycia");
        jButtonCoverabilityGraph.setToolTipText("prawie graf osiągalności");
        jButtonCoverabilityGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCoverabilityGraphActionPerformed(evt);
            }
        });

        jButtonBoundedness.setText("Ograniczoność sieci");
        jButtonBoundedness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBoundednessActionPerformed(evt);
            }
        });

        jButtonConservation.setText("Zachowawczość sieci");
        jButtonConservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConservationActionPerformed(evt);
            }
        });

        isSelectionByUser.setText("Możliwość wyboru przejścia");
        isSelectionByUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isSelectionByUserActionPerformed(evt);
            }
        });

        jButtonReversibility.setText("Odwracalność sieci");
        jButtonReversibility.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReversibilityActionPerformed(evt);
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
                    .addComponent(jToggleButtonSymulacja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sldDeley, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelActionsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDelayVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonReachabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCoverabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBoundedness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isSelectionByUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReversibility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReachabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCoverabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBoundedness)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConservation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReversibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(isSelectionByUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Zapisz sieć");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Wczytaj sieć");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem6.setText("Wyczyść");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        menuBar.add(jMenu4);

        elmViews.setText("Widok");

        jMenuItem5.setText("Macierz");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        elmViews.add(jMenuItem5);

        mitOsi.setText("Osiągalność");
        mitOsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitOsiActionPerformed(evt);
            }
        });
        elmViews.add(mitOsi);

        mitPokrycia.setText("Pokrycie");
        elmViews.add(mitPokrycia);

        menuBar.add(elmViews);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelActions, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createMenu(EditingModalGraphMouse2 gm) {
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null);
        modeMenu.setPreferredSize(new Dimension(80, 20));
        menuBar.add(modeMenu);
    }

    private void jButtonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActiveActionPerformed
        JOptionPane.showMessageDialog(vv, (graph.updateGraphTransitionStates() ? "Wszystkie" : "Nie wszystkie") + " przejścia są aktywne.",
                "Aktywność sieci", JOptionPane.INFORMATION_MESSAGE);
        //System.out.println("Wszystkie przejścia są aktywne: " + graph.updateGraphTransitionStates());
    }//GEN-LAST:event_jButtonActiveActionPerformed

    private void jButtonNplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNplusActionPerformed
        System.out.println("Macierz N+: " + java.util.Arrays.deepToString(graph.getNplus()));
        drawTable(graph.getNplus());
    }//GEN-LAST:event_jButtonNplusActionPerformed

    private void jButtonNminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNminusActionPerformed
        System.out.println("Macierz N-: " + java.util.Arrays.deepToString(graph.getNminus()));
        drawTable(graph.getNminus());
    }//GEN-LAST:event_jButtonNminusActionPerformed

    private void jButtonIncidenceMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncidenceMatrixActionPerformed
        System.out.println("Macierz incydencji: " + java.util.Arrays.deepToString(graph.getNincidence()));
        drawTable(graph.getNincidence());
    }//GEN-LAST:event_jButtonIncidenceMatrixActionPerformed

    private void blockOptions(boolean doWeBlock)
    {
            jButtonActive.setEnabled(!doWeBlock);
            jButtonNplus.setEnabled(!doWeBlock);
            jButtonNminus.setEnabled(!doWeBlock);
            jButtonIncidenceMatrix.setEnabled(!doWeBlock);
            jButtonReachabilityGraph.setEnabled(!doWeBlock);
            jButtonCoverabilityGraph.setEnabled(!doWeBlock);
            jButtonBoundedness.setEnabled(!doWeBlock);
            jButtonConservation.setEnabled(!doWeBlock);
            jMenu4.setEnabled(!doWeBlock);
            elmViews.setEnabled(!doWeBlock);
            isSelectionByUser.setEnabled(!doWeBlock);

    }
    private void drawTable(int[][] matrix) {
        if (matrixForm == null) {
            matrixForm = new MatrixForm();
        }

        matrixForm.setVisible(true);
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        matrixForm.drawTable(matrix, graph.getTransitionSet(), graph.getPlaceSet());
    }

    private void jToggleButtonSymulacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSymulacjaActionPerformed
        if (jToggleButtonSymulacja.isSelected()) {
            blockOptions(true);
            if(!isSelectionByUser.isSelected()) {
                simulationThread = new Thread(simulationPetriGraph);
                simulationThread.start();
            }
            else{
                PluggableGraphMouse simulationGraphMouse = new PluggableGraphMouse();
                simulationGraphMouse.add(simulateGraphMousePlugin);
                vv.setGraphMouse(simulationGraphMouse);
            }
        }
        else{
                blockOptions(false);
                if(!jToggleButtonSymulacja.isSelected()){
                    
                    if(!isSelectionByUser.isSelected()){ 
                        simulationThread.interrupt();
                    }
                    else{
                        isSelectionByUser.setEnabled(true);
                        vv.setGraphMouse(gm);
                    }
                }

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
        //new SaveLoadGui('s').setVisible(true);
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz gdzie zapisać");
        fileChooser.setSelectedFile(new File("PetriNet"));

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(fileToSave);
                oos = new ObjectOutputStream(fos);

                oos.writeObject(this.graph); //serializacja obiektu
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                } catch (IOException e) {
                }
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //new SaveLoadGui('l').setVisible(true);
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz skąd wczytać plik");
        //fileChooser.setSelectedFile(new File(txtFileName.getText()));

        int userSelection = fileChooser.showOpenDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            System.out.println("Read from file: " + fileToOpen.getAbsolutePath());
            System.out.println(graph.toString());
            try {
                FileInputStream fileIn = new FileInputStream(fileToOpen);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.graph = (PetriGraph) in.readObject();
                vv.setGraphLayout(new KKLayout(graph));
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("PetriNet class not found");
                c.printStackTrace();
                return;
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        drawTable(null);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void sldDeleyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldDeleyStateChanged
        lblDelayVal.setText(Integer.toString(sldDeley.getValue()) + " ms");
        simulationPetriGraph.setDelay(sldDeley.getValue());
    }//GEN-LAST:event_sldDeleyStateChanged

    private void jButtonReachabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReachabilityGraphActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_jButtonReachabilityGraphActionPerformed

    private void drawRechabilityGrap() {
        if (reachabilityGraphForm == null) {
            reachabilityGraphForm = new ReachabilityGraphForm();
        }

        reachabilityGraphForm.setVisible(true);
        reachabilityGraphForm.calculateReachabilityGraph(graph);
    }

    private void jButtonCoverabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCoverabilityGraphActionPerformed
        Transformer<Map<Place, Integer>, String> vlt = new Transformer<Map<Place, Integer>, String>() {
            public String transform(Map<Place, Integer> map) {
                String label = "";
                Place[] places = map.keySet().toArray(new Place[map.keySet().size()]);
                Arrays.sort(places);
                for (Place p : places) {
                    label += "," +/*Integer.toString(p.getId())+":"+*/ ((map.get(p) == -1) ? "∞ " : map.get(p));
                }
                return label.substring(1);
            }
        };
        //Showgraph.showGraph(this.graph.getReachabilityGraph(), vlt, "ReachabilityGraph", 500, 300);
        Showgraph.showGraph(this.graph.getCoverabilityGraphv2(), vlt, "CoverabilityGraph", 500, 300);
    }//GEN-LAST:event_jButtonCoverabilityGraphActionPerformed

    private void mitOsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitOsiActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_mitOsiActionPerformed

    private void jButtonBoundednessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBoundednessActionPerformed
        //System.out.println(graph.getPlacesBoundedness().toString());
        int boundedness = graph.getGraphBoundedness();
        JOptionPane.showMessageDialog(vv, "Sieć jest " + (boundedness == -1 ? "nie" : boundedness + "-") + "ograniczona.",
                "Ograniczoność sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonBoundednessActionPerformed

    private void jButtonConservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConservationActionPerformed
        boolean conservation = graph.getSimpleGraphConservation();
        JOptionPane.showMessageDialog(vv, "Sieć " + (conservation ? "" : "nie ") + "jest zachowawcza.",
                "Zachowawczość sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonConservationActionPerformed

    private void isSelectionByUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isSelectionByUserActionPerformed

    }//GEN-LAST:event_isSelectionByUserActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButtonReversibilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReversibilityActionPerformed
        boolean reversibility = graph.getGraphReversibility();
        JOptionPane.showMessageDialog(vv, "Sieć " + (reversibility ? "" : "nie ") + "jest odwracalna.",
                "Odwracalność sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonReversibilityActionPerformed

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
    private javax.swing.JCheckBox isSelectionByUser;
    private javax.swing.JButton jButtonActive;
    private javax.swing.JButton jButtonBoundedness;
    private javax.swing.JButton jButtonConservation;
    private javax.swing.JButton jButtonCoverabilityGraph;
    private javax.swing.JButton jButtonIncidenceMatrix;
    private javax.swing.JButton jButtonNminus;
    private javax.swing.JButton jButtonNplus;
    private javax.swing.JButton jButtonReachabilityGraph;
    private javax.swing.JButton jButtonReversibility;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JToggleButton jToggleButtonSymulacja;
    private javax.swing.JLabel lblDelayVal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mitOsi;
    private javax.swing.JMenuItem mitPokrycia;
    private javax.swing.JSlider sldDeley;
    // End of variables declaration//GEN-END:variables
}
