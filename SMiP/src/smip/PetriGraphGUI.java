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
 * @author Elpidiusz
 */
public class PetriGraphGUI extends javax.swing.JFrame {

    private PetriGraph graph;
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
    
    private SimulateGraphMousePlugin simulateGraphMousePlugin = new SimulateGraphMousePlugin();
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

        pnlGraph.setSize(600, 400);

        Layout<MyVertex, Arc> layout = new StaticLayout(graph);
        layout.setSize(this.pnlGraph.getSize());

        vv = new VisualizationViewer<>(layout);
        vv.setPreferredSize(this.pnlGraph.getSize());
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

        pnlGraph.add(vv);
        pnlGraph.validate();
        pnlGraph.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlActions = new javax.swing.JPanel();
        btnActive = new javax.swing.JButton();
        btnNplus = new javax.swing.JButton();
        btnNminus = new javax.swing.JButton();
        btnIncidenceMatrix = new javax.swing.JButton();
        tbnSymulacja = new javax.swing.JToggleButton();
        sldDeley = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        lblDelayVal = new javax.swing.JLabel();
        btnReachabilityGraph = new javax.swing.JButton();
        btnCoverabilityGraph = new javax.swing.JButton();
        btnBoundedness = new javax.swing.JButton();
        btnConservation = new javax.swing.JButton();
        chkIsSelectionByUser = new javax.swing.JCheckBox();
        btnReversibility = new javax.swing.JButton();
        pnlGraph = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        elmAbout = new javax.swing.JMenu();
        mitAuthors = new javax.swing.JMenuItem();
        mitLicence = new javax.swing.JMenuItem();
        elmNet = new javax.swing.JMenu();
        mitSaveNet = new javax.swing.JMenuItem();
        mitLoadNet = new javax.swing.JMenuItem();
        mitClearNet = new javax.swing.JMenuItem();
        elmViews = new javax.swing.JMenu();
        mitMatrix = new javax.swing.JMenuItem();
        mitOsi = new javax.swing.JMenuItem();
        mitPokrycia = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sieć miejsc i przejsć");
        setName("main_frame"); // NOI18N

        pnlActions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlActions.setToolTipText("Panel z akcjami do wykonania na grafie");
        pnlActions.setPreferredSize(new java.awt.Dimension(112, 400));

        btnActive.setText("Aktywność");
        btnActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveActionPerformed(evt);
            }
        });

        btnNplus.setText("Macierz wejść");
        btnNplus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNplusActionPerformed(evt);
            }
        });

        btnNminus.setText("Macierz wyjść");
        btnNminus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNminusActionPerformed(evt);
            }
        });

        btnIncidenceMatrix.setText("Macierz incydecji");
        btnIncidenceMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncidenceMatrixActionPerformed(evt);
            }
        });

        tbnSymulacja.setText("Symuluj");
        tbnSymulacja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnSymulacjaActionPerformed(evt);
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

        btnReachabilityGraph.setText("Graf osiągalności");
        btnReachabilityGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReachabilityGraphActionPerformed(evt);
            }
        });

        btnCoverabilityGraph.setText("Graf pokrycia");
        btnCoverabilityGraph.setToolTipText("prawie graf osiągalności");
        btnCoverabilityGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoverabilityGraphActionPerformed(evt);
            }
        });

        btnBoundedness.setText("Ograniczoność sieci");
        btnBoundedness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoundednessActionPerformed(evt);
            }
        });

        btnConservation.setText("Zachowawczość sieci");
        btnConservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConservationActionPerformed(evt);
            }
        });

        chkIsSelectionByUser.setText("Możliwość wyboru przejścia");
        chkIsSelectionByUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIsSelectionByUserActionPerformed(evt);
            }
        });

        btnReversibility.setText("Odwracalność sieci");
        btnReversibility.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReversibilityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionsLayout = new javax.swing.GroupLayout(pnlActions);
        pnlActions.setLayout(pnlActionsLayout);
        pnlActionsLayout.setHorizontalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNplus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNminus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncidenceMatrix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnSymulacja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sldDeley, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDelayVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnReachabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCoverabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoundedness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkIsSelectionByUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReversibility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlActionsLayout.setVerticalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNplus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNminus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIncidenceMatrix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReachabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCoverabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBoundedness)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConservation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReversibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(chkIsSelectionByUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDelayVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldDeley, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbnSymulacja)
                .addContainerGap())
        );

        pnlGraph.setToolTipText("Panel z grafem");
        pnlGraph.setPreferredSize(new java.awt.Dimension(600, 400));

        elmAbout.setText("O programie");

        mitAuthors.setText("Autorzy");
        mitAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitAuthorsActionPerformed(evt);
            }
        });
        elmAbout.add(mitAuthors);

        mitLicence.setText("Licencja");
        elmAbout.add(mitLicence);

        menuBar.add(elmAbout);

        elmNet.setText("Sieć");

        mitSaveNet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mitSaveNet.setText("Zapisz sieć");
        mitSaveNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitSaveNetActionPerformed(evt);
            }
        });
        elmNet.add(mitSaveNet);

        mitLoadNet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mitLoadNet.setText("Wczytaj sieć");
        mitLoadNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitLoadNetActionPerformed(evt);
            }
        });
        elmNet.add(mitLoadNet);

        mitClearNet.setText("Wyczyść");
        mitClearNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitClearNetActionPerformed(evt);
            }
        });
        elmNet.add(mitClearNet);

        menuBar.add(elmNet);

        elmViews.setText("Widok");

        mitMatrix.setText("Macierz");
        mitMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitMatrixActionPerformed(evt);
            }
        });
        elmViews.add(mitMatrix);

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
                .addComponent(pnlActions, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        JOptionPane.showMessageDialog(vv, (graph.updateGraphTransitionStates() ? "Wszystkie" : "Nie wszystkie") + " przejścia są aktywne.",
                "Aktywność sieci", JOptionPane.INFORMATION_MESSAGE);
        //System.out.println("Wszystkie przejścia są aktywne: " + graph.updateGraphTransitionStates());
    }//GEN-LAST:event_btnActiveActionPerformed

    private void btnNplusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNplusActionPerformed
        System.out.println("Macierz N+: " + java.util.Arrays.deepToString(graph.getNplus()));
        drawTable(graph.getNplus());
    }//GEN-LAST:event_btnNplusActionPerformed

    private void btnNminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNminusActionPerformed
        System.out.println("Macierz N-: " + java.util.Arrays.deepToString(graph.getNminus()));
        drawTable(graph.getNminus());
    }//GEN-LAST:event_btnNminusActionPerformed

    private void btnIncidenceMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncidenceMatrixActionPerformed
        System.out.println("Macierz incydencji: " + java.util.Arrays.deepToString(graph.getNincidence()));
        drawTable(graph.getNincidence());
    }//GEN-LAST:event_btnIncidenceMatrixActionPerformed

    private void blockOptions(boolean doWeBlock)
    {
            btnActive.setEnabled(!doWeBlock);
            btnNplus.setEnabled(!doWeBlock);
            btnNminus.setEnabled(!doWeBlock);
            btnIncidenceMatrix.setEnabled(!doWeBlock);
            btnReachabilityGraph.setEnabled(!doWeBlock);
            btnCoverabilityGraph.setEnabled(!doWeBlock);
            btnBoundedness.setEnabled(!doWeBlock);
            btnConservation.setEnabled(!doWeBlock);
            elmNet.setEnabled(!doWeBlock);
            elmViews.setEnabled(!doWeBlock);
            chkIsSelectionByUser.setEnabled(!doWeBlock);

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

    private void tbnSymulacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnSymulacjaActionPerformed
        if (tbnSymulacja.isSelected()) {
            blockOptions(true);
            if(!chkIsSelectionByUser.isSelected()) {
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
                if(!tbnSymulacja.isSelected()){
                    
                    if(!chkIsSelectionByUser.isSelected()){ 
                        simulationThread.interrupt();
                    }
                    else{
                        chkIsSelectionByUser.setEnabled(true);
                        vv.setGraphMouse(gm);
                    }
                }

        }
        
    }//GEN-LAST:event_tbnSymulacjaActionPerformed

    private void mitAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitAuthorsActionPerformed

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
    }//GEN-LAST:event_mitAuthorsActionPerformed

    private void mitSaveNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitSaveNetActionPerformed
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
    }//GEN-LAST:event_mitSaveNetActionPerformed

    private void mitLoadNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitLoadNetActionPerformed
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
            } catch (ClassNotFoundException c) {
                System.out.println("PetriNet class not found");
                c.printStackTrace();
            }
        }
    }//GEN-LAST:event_mitLoadNetActionPerformed

    private void mitMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitMatrixActionPerformed
        drawTable(null);
    }//GEN-LAST:event_mitMatrixActionPerformed

    private void sldDeleyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldDeleyStateChanged
        lblDelayVal.setText(Integer.toString(sldDeley.getValue()) + " ms");
        simulationPetriGraph.setDelay(sldDeley.getValue());
    }//GEN-LAST:event_sldDeleyStateChanged

    private void btnReachabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReachabilityGraphActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_btnReachabilityGraphActionPerformed

    private void drawRechabilityGrap() {
        if (reachabilityGraphForm == null) {
            reachabilityGraphForm = new ReachabilityGraphForm();
        }

        reachabilityGraphForm.setVisible(true);
        reachabilityGraphForm.calculateReachabilityGraph(graph);
    }

    private void btnCoverabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoverabilityGraphActionPerformed
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
        Showgraph.showGraph(this.graph.getCoverabilityGraphv2(), vlt, "CoverabilityGraph", 500, 300);
    }//GEN-LAST:event_btnCoverabilityGraphActionPerformed

    private void mitOsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitOsiActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_mitOsiActionPerformed

    private void btnBoundednessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoundednessActionPerformed
        int boundedness = graph.getGraphBoundedness();
        JOptionPane.showMessageDialog(vv, "Sieć jest " + (boundedness == -1 ? "nie" : boundedness + "-") + "ograniczona.",
                "Ograniczoność sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnBoundednessActionPerformed

    private void btnConservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConservationActionPerformed
        boolean conservation = graph.getSimpleGraphConservation();
        JOptionPane.showMessageDialog(vv, "Sieć " + (conservation ? "" : "nie ") + "jest zachowawcza.",
                "Zachowawczość sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConservationActionPerformed

    private void chkIsSelectionByUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIsSelectionByUserActionPerformed

    }//GEN-LAST:event_chkIsSelectionByUserActionPerformed

    private void mitClearNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitClearNetActionPerformed

        
// TODO add your handling code here:
    }//GEN-LAST:event_mitClearNetActionPerformed

    private void btnReversibilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReversibilityActionPerformed
        boolean reversibility = graph.getGraphReversibility();
        JOptionPane.showMessageDialog(vv, "Sieć " + (reversibility ? "" : "nie ") + "jest odwracalna.",
                "Odwracalność sieci", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnReversibilityActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetriGraphGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetriGraphGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnBoundedness;
    private javax.swing.JButton btnConservation;
    private javax.swing.JButton btnCoverabilityGraph;
    private javax.swing.JButton btnIncidenceMatrix;
    private javax.swing.JButton btnNminus;
    private javax.swing.JButton btnNplus;
    private javax.swing.JButton btnReachabilityGraph;
    private javax.swing.JButton btnReversibility;
    private javax.swing.JCheckBox chkIsSelectionByUser;
    private javax.swing.JMenu elmAbout;
    private javax.swing.JMenu elmNet;
    private javax.swing.JMenu elmViews;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDelayVal;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mitAuthors;
    private javax.swing.JMenuItem mitClearNet;
    private javax.swing.JMenuItem mitLicence;
    private javax.swing.JMenuItem mitLoadNet;
    private javax.swing.JMenuItem mitMatrix;
    private javax.swing.JMenuItem mitOsi;
    private javax.swing.JMenuItem mitPokrycia;
    private javax.swing.JMenuItem mitSaveNet;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlGraph;
    private javax.swing.JSlider sldDeley;
    private javax.swing.JToggleButton tbnSymulacja;
    // End of variables declaration//GEN-END:variables
}
