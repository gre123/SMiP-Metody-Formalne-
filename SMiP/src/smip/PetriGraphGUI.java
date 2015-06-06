package smip;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

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
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
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
import model.factory.ArcFactory;
import model.factory.PlaceTransitionFactory;
import mouse.CheckingMouse.ArcChecker;
import mouse.CheckingMouse.EditingCheckingGraphMousePlugin;
import mouse.CheckingMouse.EditingModalGraphMouse2;
import mouse.CheckingMouse.MyVertexChecker;
import mouse.MousePlugin.SimulateGraphMousePlugin;
import mouse.PopupMenu.MyMouseMenus.EdgeMenu;
import mouse.PopupMenu.MyMouseMenus.VertexMenu;
import mouse.PopupMenu.PopupVertexEdgeMenuMousePlugin;
import org.apache.commons.collections15.Transformer;
import painter.BoundednessLabeller;
import painter.MyVertexSimpleShapePainter;
import painter.NullTransformer;
import painter.TransitionAlivenessColorPainter;
import smip.views.BoundaryWeightsForm;
import smip.views.ShowGraph;

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
    Properties properties;
    
    private SimulateGraphMousePlugin simulateGraphMousePlugin;

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
        properties = new Properties(graph);
        
        setProperties();
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
        plugin.setProperites(properties);
        
        gm = new EditingModalGraphMouse2(vv.getRenderContext(), vertexFactory, edgeFactory);
        
        gm.remove(gm.getEditingPlugin());
        plugin.setVertexChecker(vCheck);
        plugin.setEdgeChecker(eCheck);
        gm.setEditingPlugin(plugin);
        
        PopupVertexEdgeMenuMousePlugin myPlugin = new PopupVertexEdgeMenuMousePlugin();
        JPopupMenu edgeMenu = new EdgeMenu();
        JPopupMenu vertexMenu = new VertexMenu(this);
        myPlugin.setEdgePopup(edgeMenu);
        myPlugin.setVertexPopup(vertexMenu);
        gm.remove(gm.getPopupEditingPlugin());  // Removes the existing popup editing plugin
        gm.add(myPlugin);   // Add our new plugin to the mouse

        vv.setGraphMouse(gm);
        
        createMenu(gm);
        gm.setMode(ModalGraphMouse.Mode.EDITING);
        simulationPetriGraph = new RunnableSimulationPetriGraph(graph, vv);
        simulationPetriGraph.setProperties(properties);
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

        pnlGraph = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlActions = new javax.swing.JPanel();
        btnReachabilityGraph = new javax.swing.JButton();
        btnCoverabilityGraph = new javax.swing.JButton();
        tgbtnBoundednessPlaces = new javax.swing.JToggleButton();
        tgbtnL1TransitionsLiveness = new javax.swing.JToggleButton();
        chkRefresh = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblActivity = new javax.swing.JLabel();
        lblBoundedness = new javax.swing.JLabel();
        lblConservation = new javax.swing.JLabel();
        lblReversibility = new javax.swing.JLabel();
        lblL1Liveness = new javax.swing.JLabel();
        lblL4Liveness = new javax.swing.JLabel();
        btnWeightedConservation = new javax.swing.JButton();
        jCheckBoxVertexLabel = new javax.swing.JCheckBox();
        jCheckBoxEdgeLabel = new javax.swing.JCheckBox();
        pnlSimulation = new javax.swing.JPanel();
        chkIsSelectionByUser = new javax.swing.JCheckBox();
        lblDelayVal = new javax.swing.JLabel();
        tbnSimulate = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        sldDeley = new javax.swing.JSlider();
        spnSteps = new javax.swing.JSpinner();
        tbnStep = new javax.swing.JToggleButton();
        jLabel8 = new javax.swing.JLabel();
        lblTimeDiff = new javax.swing.JLabel();
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
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sieć miejsc i przejsć");
        setName("main_frame"); // NOI18N

        pnlGraph.setToolTipText("Panel z grafem");
        pnlGraph.setPreferredSize(new java.awt.Dimension(600, 457));
        pnlGraph.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlGraphComponentResized(evt);
            }
        });

        pnlActions.setToolTipText("Panel z akcjami do wykonania na grafie");
        pnlActions.setPreferredSize(new java.awt.Dimension(112, 400));

        btnReachabilityGraph.setText("Graf osiągalności");
        btnReachabilityGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReachabilityGraphMouseClicked(evt);
            }
        });
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

        tgbtnBoundednessPlaces.setText("Ograniczoność miejsc");
        tgbtnBoundednessPlaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnBoundednessPlacesActionPerformed(evt);
            }
        });
        tgbtnBoundednessPlaces.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgbtnBoundednessPlacesPropertyChange(evt);
            }
        });

        tgbtnL1TransitionsLiveness.setText("L1-żywotność miejsc");
        tgbtnL1TransitionsLiveness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnL1TransitionsLivenessActionPerformed(evt);
            }
        });

        chkRefresh.setSelected(true);
        chkRefresh.setText("odświeżaj");
        chkRefresh.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRefreshStateChanged(evt);
            }
        });
        chkRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkRefreshMouseClicked(evt);
            }
        });

        jLabel2.setText("Aktywność:");

        jLabel3.setText("Ograniczoność:");

        jLabel4.setText("Zachowawczość:");

        jLabel5.setText("Odwracalność:");

        jLabel6.setText("Żywotność L1:");

        jLabel7.setText("Żywotność L4:");

        lblActivity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActivity.setText("-");

        lblBoundedness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBoundedness.setText("-");

        lblConservation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConservation.setText("-");

        lblReversibility.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblReversibility.setText("-");

        lblL1Liveness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblL1Liveness.setText("-");

        lblL4Liveness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblL4Liveness.setText("-");

        btnWeightedConservation.setText("Ważona zachowawczość");
        btnWeightedConservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWeightedConservationActionPerformed(evt);
            }
        });

        jCheckBoxVertexLabel.setSelected(true);
        jCheckBoxVertexLabel.setText("etykiety wierzchołków");
        jCheckBoxVertexLabel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxVertexLabelStateChanged(evt);
            }
        });
        jCheckBoxVertexLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVertexLabelActionPerformed(evt);
            }
        });

        jCheckBoxEdgeLabel.setSelected(true);
        jCheckBoxEdgeLabel.setText("etykiety łuków");
        jCheckBoxEdgeLabel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxEdgeLabelStateChanged(evt);
            }
        });
        jCheckBoxEdgeLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEdgeLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionsLayout = new javax.swing.GroupLayout(pnlActions);
        pnlActions.setLayout(pnlActionsLayout);
        pnlActionsLayout.setHorizontalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgbtnL1TransitionsLiveness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReachabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCoverabilityGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgbtnBoundednessPlaces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlActionsLayout.createSequentialGroup()
                        .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 124, Short.MAX_VALUE)
                        .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblActivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBoundedness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblReversibility))
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblL1Liveness))
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblL4Liveness))
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblConservation))
                    .addComponent(btnWeightedConservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlActionsLayout.createSequentialGroup()
                        .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxEdgeLabel)
                            .addComponent(jCheckBoxVertexLabel)
                            .addComponent(chkRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlActionsLayout.setVerticalGroup(
            pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblActivity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblBoundedness))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblConservation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblReversibility))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblL1Liveness))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblL4Liveness))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxVertexLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxEdgeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnReachabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCoverabilityGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgbtnBoundednessPlaces)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnWeightedConservation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgbtnL1TransitionsLiveness)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Właściwości", pnlActions);

        chkIsSelectionByUser.setText("Możliwość wyboru przejścia");
        chkIsSelectionByUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIsSelectionByUserActionPerformed(evt);
            }
        });

        lblDelayVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDelayVal.setText("1000 ms");

        tbnSimulate.setText("Symuluj ∞");
        tbnSimulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnSimulateActionPerformed(evt);
            }
        });

        jLabel1.setText("Opóźnienie:");

        sldDeley.setMajorTickSpacing(200);
        sldDeley.setMaximum(1200);
        sldDeley.setMinimum(50);
        sldDeley.setMinorTickSpacing(50);
        sldDeley.setPaintTicks(true);
        sldDeley.setSnapToTicks(true);
        sldDeley.setValue(1000);
        sldDeley.setPreferredSize(new java.awt.Dimension(50, 23));
        sldDeley.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldDeleyStateChanged(evt);
            }
        });

        spnSteps.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spnSteps.setValue(1);

        tbnStep.setText("Krok →");
        tbnStep.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbnStepStateChanged(evt);
            }
        });
        tbnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnStepActionPerformed(evt);
            }
        });

        jLabel8.setText("Czas przetwarzania kroku:");

        lblTimeDiff.setText("0 ms");

        javax.swing.GroupLayout pnlSimulationLayout = new javax.swing.GroupLayout(pnlSimulation);
        pnlSimulation.setLayout(pnlSimulationLayout);
        pnlSimulationLayout.setHorizontalGroup(
            pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSimulationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSimulationLayout.createSequentialGroup()
                        .addComponent(tbnSimulate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlSimulationLayout.createSequentialGroup()
                        .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sldDeley, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlSimulationLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDelayVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlSimulationLayout.createSequentialGroup()
                                .addComponent(spnSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tbnStep)))
                        .addGap(9, 9, 9))
                    .addGroup(pnlSimulationLayout.createSequentialGroup()
                        .addComponent(chkIsSelectionByUser)
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(pnlSimulationLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTimeDiff)
                        .addContainerGap())))
        );
        pnlSimulationLayout.setVerticalGroup(
            pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSimulationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTimeDiff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addComponent(chkIsSelectionByUser)
                .addGap(18, 18, 18)
                .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDelayVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sldDeley, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbnSimulate)
                .addGap(4, 4, 4)
                .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnStep))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Symulacja", pnlSimulation);

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

        mitClearNet.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mitClearNet.setText("Wyczyść");
        mitClearNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitClearNetActionPerformed(evt);
            }
        });
        elmNet.add(mitClearNet);

        menuBar.add(elmNet);

        elmViews.setText("Widok");

        mitMatrix.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        mitMatrix.setText("Macierz");
        mitMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitMatrixActionPerformed(evt);
            }
        });
        elmViews.add(mitMatrix);

        mitOsi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        mitOsi.setText("Osiągalność");
        mitOsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitOsiActionPerformed(evt);
            }
        });
        elmViews.add(mitOsi);

        mitPokrycia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        mitPokrycia.setText("Pokrycie");
        mitPokrycia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitPokryciaActionPerformed(evt);
            }
        });
        elmViews.add(mitPokrycia);

        jMenu1.setText("Tryb");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("tryb1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("tryb2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("tryb3");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("tryb4");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("tryb5");
        jMenuItem5.setEnabled(false);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("tryb6");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        elmViews.add(jMenu1);

        menuBar.add(elmViews);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void setProperties() {
        properties.setLblActivity(lblActivity);
        properties.setLblBoundedness(lblBoundedness);
        properties.setLblConservation(lblConservation);
        properties.setLblL1Liveness(lblL1Liveness);
        properties.setLblL4Liveness(lblL4Liveness);
        properties.setLblReversibility(lblReversibility);
        properties.setMatrixForm(matrixForm);
    }
    
    private void createMenu(EditingModalGraphMouse2 gm) {
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null);
        modeMenu.setPreferredSize(new Dimension(80, 20));
        menuBar.add(modeMenu);
    }
    
    private void blockOptions(boolean doWeBlock) {
        btnReachabilityGraph.setEnabled(!doWeBlock);
        btnCoverabilityGraph.setEnabled(!doWeBlock);
        elmNet.setEnabled(!doWeBlock);
        elmViews.setEnabled(!doWeBlock);
        chkIsSelectionByUser.setEnabled(!doWeBlock);
        tgbtnBoundednessPlaces.setEnabled(!doWeBlock);
        tbnSimulate.setEnabled(!doWeBlock);
        tbnStep.setEnabled(!doWeBlock);
        tgbtnL1TransitionsLiveness.setEnabled(!doWeBlock);
        btnWeightedConservation.setEnabled(!doWeBlock);
    }
    
    private void drawTables() {
        if (matrixForm == null) {
            matrixForm = new MatrixForm();
            matrixForm.setTitle("Reprezentacja macierzowa");
            
        }
        Point location = this.getLocation();
        location.x = location.x + this.getSize().width;
        matrixForm.setLocation(location);
        properties.setMatrixForm(matrixForm);
        matrixForm.setVisible(true);
        if (graph.getNincidence() == null || graph.getNincidence().length == 0 || graph.getNincidence()[0].length == 0) {
            return;
        }
        matrixForm.drawInc(graph.getNincidence(), graph.getTransitionSet(), graph.getPlaceSet());
        matrixForm.drawNplus(graph.getNplus(), graph.getTransitionSet(), graph.getPlaceSet());
        matrixForm.drawNminus(graph.getNminus(), graph.getTransitionSet(), graph.getPlaceSet());
        
    }
    

    private void tbnSimulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnSimulateActionPerformed
        if (tbnSimulate.isSelected()) {
            startSimulate(-1, tbnSimulate);
        } else {
            stopSimulate();
        }
    }//GEN-LAST:event_tbnSimulateActionPerformed

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
        JFrame parentFrame = new JFrame();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz gdzie zapisać");
        fileChooser.setSelectedFile(new File("PetriNet.pn"));
        
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            
            try {
                fos = new FileOutputStream(fileToSave);
                oos = new ObjectOutputStream(fos);
                for (Object v : vv.getGraphLayout().getGraph().getVertices()) {
                    Object o = vv.getGraphLayout().transform(v);
                    for (MyVertex vg : graph.getAllVertices()) {
                        if (((MyVertex) v).compareTo(vg) == 0 && v.getClass().equals(vg.getClass())) {
                            vg.setX(((Point2D) o).getX());
                            vg.setY(((Point2D) o).getY());
                        }
                        
                    }
                }
                oos.writeObject(graph); //serializacja obiektu
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
        JFrame parentFrame = new JFrame();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz skąd wczytać plik");
        
        int userSelection = fileChooser.showOpenDialog(parentFrame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            System.out.println("Read from file: " + fileToOpen.getAbsolutePath());
            try {
                FileInputStream fileIn = new FileInputStream(fileToOpen);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                graph = (PetriGraph) in.readObject();
                properties = new Properties(graph);
                setProperties();
                Layout<MyVertex, Arc> layout = new StaticLayout(graph);
                for (MyVertex v : graph.getAllVertices()) {
                    layout.setLocation(v, new Point2D.Double(v.getX(), v.getY()));;
                }
                vv.setGraphLayout(layout);
                simulationPetriGraph = new RunnableSimulationPetriGraph(graph, vv);
                simulationPetriGraph.setProperties(properties);
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
    }//GEN-LAST:event_mitLoadNetActionPerformed

    private void mitMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitMatrixActionPerformed
        drawTables();
    }//GEN-LAST:event_mitMatrixActionPerformed

    private void sldDeleyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldDeleyStateChanged
        lblDelayVal.setText(Integer.toString(sldDeley.getValue()) + " ms");
        simulationPetriGraph.setDelay(sldDeley.getValue());
    }//GEN-LAST:event_sldDeleyStateChanged
    
    private void drawRechabilityGrap() {
        if (reachabilityGraphForm == null) {
            
        } else {
            reachabilityGraphForm.setVisible(false);
            reachabilityGraphForm.dispose();
        }
        
        reachabilityGraphForm = new ReachabilityGraphForm();
        reachabilityGraphForm.setTitle("Graf osiągalności");
        
        Point location = this.getLocation();
        location.x = location.x + this.getSize().width;
        reachabilityGraphForm.setLocation(location);
        
        reachabilityGraphForm.setVisible(true);
        reachabilityGraphForm.calculateReachabilityGraph(graph);
    }

    private void mitOsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitOsiActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_mitOsiActionPerformed

    private void chkIsSelectionByUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIsSelectionByUserActionPerformed

    }//GEN-LAST:event_chkIsSelectionByUserActionPerformed

    private void mitClearNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitClearNetActionPerformed
        graph.clear();
        vv.repaint();
    }//GEN-LAST:event_mitClearNetActionPerformed
    
    private void startSimulate(int param, JToggleButton button) {
        blockOptions(true);
        button.setEnabled(true);
        simulationPetriGraph.setTransitionPerStep(param);
        simulationPetriGraph.setButton(button);
        simulationPetriGraph.setLblTimeDiff(lblTimeDiff);
        if (!chkIsSelectionByUser.isSelected()) {
            simulationThread = new Thread(simulationPetriGraph);
            simulationThread.start();
        } else {
            simulateGraphMousePlugin = new SimulateGraphMousePlugin(graph);
            PluggableGraphMouse simulationGraphMouse = new PluggableGraphMouse();
            simulationGraphMouse.add(simulateGraphMousePlugin);
            vv.setGraphMouse(simulationGraphMouse);
        }
    }
    
    private void stopSimulate() {
        blockOptions(false);
        
        if (!chkIsSelectionByUser.isSelected()) {
            simulationThread.interrupt();
        } else {
            chkIsSelectionByUser.setEnabled(true);
            vv.setGraphMouse(gm);
        }
    }

    private void tbnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnStepActionPerformed
        if (tbnStep.isSelected()) {
            startSimulate((int) spnSteps.getValue() - 1, tbnStep);
        } else {
            stopSimulate();
        }
    }//GEN-LAST:event_tbnStepActionPerformed

    private void pnlGraphComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlGraphComponentResized
        if (vv != null) {
            vv.setPreferredSize(this.pnlGraph.getSize());
        }
    }//GEN-LAST:event_pnlGraphComponentResized

    private void tbnStepStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbnStepStateChanged
        if (!tbnStep.isSelected() && simulationThread != null) {
            stopSimulate();
        }
    }//GEN-LAST:event_tbnStepStateChanged

    private void tgbtnL1TransitionsLivenessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnL1TransitionsLivenessActionPerformed
        if (this.tgbtnL1TransitionsLiveness.isSelected()) {
            blockOptions(true);
            tgbtnL1TransitionsLiveness.setEnabled(true);
            graph.calculateAndSetTransitionsL1Liveness();
            vv.getRenderContext().setVertexFillPaintTransformer(new TransitionAlivenessColorPainter());
            vv.repaint();
        } else {
            blockOptions(false);
            vv.getRenderContext().setVertexFillPaintTransformer(new MyVertexColorPainter());
            vv.repaint();
        }
    }//GEN-LAST:event_tgbtnL1TransitionsLivenessActionPerformed

    private void tgbtnBoundednessPlacesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgbtnBoundednessPlacesPropertyChange

    }//GEN-LAST:event_tgbtnBoundednessPlacesPropertyChange

    private void tgbtnBoundednessPlacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnBoundednessPlacesActionPerformed
        if (this.tgbtnBoundednessPlaces.isSelected()) {
            blockOptions(true);
            tgbtnBoundednessPlaces.setEnabled(true);
            graph.calculateAndSetPlacesBoundedness();
            vv.getRenderContext().setVertexLabelTransformer(new BoundednessLabeller());
            vv.getRenderContext().setVertexShapeTransformer(new MyVertexSimpleShapePainter());
            vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
            vv.repaint();
        } else {
            blockOptions(false);
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
            this.jCheckBoxVertexLabel.setSelected(true);
            vv.getRenderContext().setVertexShapeTransformer(new MyVertexShapePainter());
            vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);
            vv.repaint();
        }
    }//GEN-LAST:event_tgbtnBoundednessPlacesActionPerformed

    private void btnCoverabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoverabilityGraphActionPerformed
        Transformer<Map<Place, Integer>, String> vlt = new Transformer<Map<Place, Integer>, String>() {
            public String transform(Map<Place, Integer> map) {
                String label = "";
                Place[] places = map.keySet().toArray(new Place[map.keySet().size()]);
                Arrays.sort(places);
                for (Place p : places) {
                    label += "," +/*Integer.toString(p.getId())+":"+*/ ((map.get(p) == -1) ? "∞ " : map.get(p));
                }
                if (label.equals("")) {
                    return "";
                }
                return label.substring(1);
            }
        };
        Point location = this.getLocation();
        location.x = location.x + this.getSize().width;
        ShowGraph.setLocation(location);
        ShowGraph.showRCGraph(this.graph.getCoverabilityGraph(), vlt, "Graf pokrycia", 500, 300);
    }//GEN-LAST:event_btnCoverabilityGraphActionPerformed

    private void btnReachabilityGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReachabilityGraphActionPerformed
        drawRechabilityGrap();
    }//GEN-LAST:event_btnReachabilityGraphActionPerformed

    private void btnReachabilityGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReachabilityGraphMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            Transformer<Map<Place, Integer>, String> vlt = (Map<Place, Integer> map) -> {
                String label = "";
                Place[] places = map.keySet().toArray(new Place[map.keySet().size()]);
                Arrays.sort(places);
                for (Place p : places) {
                    label += "," + map.get(p);
                }
                return label.substring(1);
            };
            ShowGraph.showRCGraph(this.graph.getReachabilityGraph(), vlt, "Graf osiągalności", 500, 300);
        }
    }//GEN-LAST:event_btnReachabilityGraphMouseClicked

    private void chkRefreshStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRefreshStateChanged
        if (properties != null) {
            properties.setRefresh(chkRefresh.isSelected());
        }
    }//GEN-LAST:event_chkRefreshStateChanged

    private void chkRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRefreshMouseClicked
        if (properties != null) {
            properties.refreshProperties();
        }
    }//GEN-LAST:event_chkRefreshMouseClicked

    private void mitPokryciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitPokryciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mitPokryciaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        vv.setGraphLayout(new StaticLayout(graph));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        vv.setGraphLayout(new KKLayout(graph));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        vv.setGraphLayout(new CircleLayout(graph));
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        vv.setGraphLayout(new ISOMLayout(graph));
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        vv.setGraphLayout(new FRLayout(graph));
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        vv.setGraphLayout(new DAGLayout(graph));
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnWeightedConservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeightedConservationActionPerformed
        BoundaryWeightsForm dialog = new BoundaryWeightsForm(this, graph.getPlaceSet(), graph);
        dialog.setLocation(btnWeightedConservation.getLocationOnScreen());
        dialog.setVisible(true);
    }//GEN-LAST:event_btnWeightedConservationActionPerformed

    private void jCheckBoxVertexLabelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxVertexLabelStateChanged

    }//GEN-LAST:event_jCheckBoxVertexLabelStateChanged

    private void jCheckBoxEdgeLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEdgeLabelActionPerformed
        if (jCheckBoxEdgeLabel.isSelected()) {
            vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
            vv.repaint();
        } else {
            vv.getRenderContext().setEdgeLabelTransformer(new NullTransformer());
            vv.repaint();
        }
    }//GEN-LAST:event_jCheckBoxEdgeLabelActionPerformed

    private void jCheckBoxVertexLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVertexLabelActionPerformed
        if (jCheckBoxVertexLabel.isSelected()) {
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
            vv.repaint();
        } else {
            vv.getRenderContext().setVertexLabelTransformer(new NullTransformer());
            vv.repaint();
        }
    }//GEN-LAST:event_jCheckBoxVertexLabelActionPerformed

    private void jCheckBoxEdgeLabelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxEdgeLabelStateChanged

    }//GEN-LAST:event_jCheckBoxEdgeLabelStateChanged

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
    private javax.swing.JButton btnCoverabilityGraph;
    private javax.swing.JButton btnReachabilityGraph;
    private javax.swing.JButton btnWeightedConservation;
    private javax.swing.JCheckBox chkIsSelectionByUser;
    private javax.swing.JCheckBox chkRefresh;
    private javax.swing.JMenu elmAbout;
    private javax.swing.JMenu elmNet;
    private javax.swing.JMenu elmViews;
    private javax.swing.JCheckBox jCheckBoxEdgeLabel;
    private javax.swing.JCheckBox jCheckBoxVertexLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblActivity;
    private javax.swing.JLabel lblBoundedness;
    private javax.swing.JLabel lblConservation;
    private javax.swing.JLabel lblDelayVal;
    private javax.swing.JLabel lblL1Liveness;
    private javax.swing.JLabel lblL4Liveness;
    private javax.swing.JLabel lblReversibility;
    private javax.swing.JLabel lblTimeDiff;
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
    private javax.swing.JPanel pnlSimulation;
    private javax.swing.JSlider sldDeley;
    private javax.swing.JSpinner spnSteps;
    private javax.swing.JToggleButton tbnSimulate;
    private javax.swing.JToggleButton tbnStep;
    private javax.swing.JToggleButton tgbtnBoundednessPlaces;
    private javax.swing.JToggleButton tgbtnL1TransitionsLiveness;
    // End of variables declaration//GEN-END:variables
}
