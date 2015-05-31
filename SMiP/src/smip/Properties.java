package smip;

import java.awt.Color;
import javax.swing.JLabel;
import model.PetriGraph;

/**
 * @author Tomek
 */
public class Properties {

    private PetriGraph graph;
    private JLabel lblActivity;
    private JLabel lblBoundedness;
    private JLabel lblConservation;
    private JLabel lblReversibility;
    private JLabel lblL1Liveness;
    private JLabel lblL4Liveness;
    private boolean refresh;

    public PetriGraph getGraph() {
        return graph;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public void setGraph(PetriGraph graph) {
        this.graph = graph;
    }

    public Properties(PetriGraph graph) {
        this.graph = graph;
        refresh = true;
    }

    public JLabel getLblActivity() {
        return lblActivity;
    }

    public void setLblActivity(JLabel lblActivity) {
        this.lblActivity = lblActivity;
    }

    public JLabel getLblBoundedness() {
        return lblBoundedness;
    }

    public void setLblBoundedness(JLabel lblBoundedness) {
        this.lblBoundedness = lblBoundedness;
    }

    public JLabel getLblConservation() {
        return lblConservation;
    }

    public void setLblConservation(JLabel lblConservation) {
        this.lblConservation = lblConservation;
    }

    public JLabel getLblReversibility() {
        return lblReversibility;
    }

    public void setLblReversibility(JLabel lblReversibility) {
        this.lblReversibility = lblReversibility;
    }

    public JLabel getLblL1Liveness() {
        return lblL1Liveness;
    }

    public void setLblL1Liveness(JLabel lblL1Liveness) {
        this.lblL1Liveness = lblL1Liveness;
    }

    public JLabel getLblL4Liveness() {
        return lblL4Liveness;
    }

    public void setLblL4Liveness(JLabel lblL4Liveness) {
        this.lblL4Liveness = lblL4Liveness;
    }

    private void setColor(JLabel label, boolean b) {
        if (b == true) {
            label.setBackground(Color.GREEN);
        } else {
            label.setBackground(Color.RED);
        }
        label.setOpaque(true);
    }

    private void refreshActivity() {
        int activeTransitons = graph.howMenyActiveTransition();
        int allTransitons = graph.getTransitionSet().size();
        lblActivity.setText(activeTransitons + "/" + allTransitons + " aktywnych przejść");
        setColor(lblActivity, activeTransitons == allTransitons);
    }

    private void refreshBoundedness() {
        int boundedness = graph.getGraphBoundedness();
        if (boundedness == -1) {
            lblBoundedness.setText("Jest nieograniczona");
        } else {
            lblBoundedness.setText("Jest " + boundedness + "-ograniczona");
        }
        setColor(lblBoundedness, boundedness == -1);
    }

    private void refreshConservation() {
        boolean conservation = graph.getSimpleGraphConservation();
        if (true == conservation) {
            lblConservation.setText("Jest zachowawcza");
        } else {
            lblConservation.setText("Nie jest zachowawcza");
        }
        setColor(lblConservation, conservation);
    }

    private void refreshReversibility() {
        boolean reversibility = graph.getGraphReversibility();
        if (true == reversibility) {
            lblReversibility.setText("Jest odwracalna");
        } else {
            lblReversibility.setText("Nie jest odwracalna");
        }
        setColor(lblReversibility, reversibility);
    }

    private void refreshL1Liveness() {
        boolean l1Liveness = graph.getGraphL1Liveness();
        if (true == l1Liveness) {
            lblL1Liveness.setText("Jest L1 - żywotna");
        } else {
            lblL1Liveness.setText("Nie jest L1 - żywotna");
        }
        setColor(lblL1Liveness, l1Liveness);
    }

    private void refreshL4Liveness() {
        boolean l4Liveness = graph.getGraphL4Liveness();
        if (true == l4Liveness) {
            lblL4Liveness.setText("Jest L4 - żywotna");
        } else {
            lblL4Liveness.setText("Nie jest L4 - żywotna");
        }
        setColor(lblL4Liveness, l4Liveness);
    }

    public void refreshProperties() {
        if (refresh) {
            refreshActivity();
            refreshBoundedness();
            refreshConservation();
            refreshReversibility();
            refreshL1Liveness();
            refreshL4Liveness();
        }

    }

}
