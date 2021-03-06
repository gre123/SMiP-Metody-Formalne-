package smip.views;

import model.Place;
import model.Transition;
import smip.table.Cell;
import smip.table.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

/**
 * @author Tomek
 */
public class MatrixForm extends javax.swing.JFrame {

    public MatrixForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMatrix = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblColNum = new javax.swing.JLabel();
        lblRowNum = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableMatrix);

        jLabel2.setText("Kolumn:");

        jLabel3.setText("Wierszy: ");

        lblColNum.setText("0");

        lblRowNum.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblColNum))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRowNum)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblColNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblRowNum))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(MatrixForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatrixForm().setVisible(true);
            }
        });
    }

    public void drawTable(int[][] matrix, Set<Transition> transitons, Set<Place> places) {

        //naglowek wiersza
        Object[] placesArray = places.toArray();
        DefaultTableModel rowHeaderTableModel = new DefaultTableModel(0, 1);
        for (int i = 0; i < places.size(); i++) {
            rowHeaderTableModel.addRow(new Object[]{placesArray[i]});
        }

        JTable dispTableRowHeader = new JTable();
        dispTableRowHeader.setModel(rowHeaderTableModel);
        dispTableRowHeader.getColumnModel().getColumn(0).setMaxWidth(40);
        dispTableRowHeader.setPreferredScrollableViewportSize(dispTableRowHeader.getPreferredSize());
        dispTableRowHeader.setDefaultRenderer(Object.class, dispTableRowHeader.getTableHeader().getDefaultRenderer());
        jScrollPane1.setRowHeaderView(dispTableRowHeader);

        //rog tabelki
        //JTableHeader corner = dispTableRowHeader.getTableHeader();
        //corner.setReorderingAllowed(false);
        //corner.setResizingAllowed(false);
        //jScrollPane1.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);


        DefaultTableModel dtm = (DefaultTableModel) tableMatrix.getModel();

        dtm.setRowCount(matrix.length);
        lblRowNum.setText(Integer.toString(matrix.length));
        dtm.setColumnCount(matrix[0].length);
        lblColNum.setText(Integer.toString(matrix[0].length));
        dtm.setColumnIdentifiers(transitons.toArray());
        tableMatrix.setModel(dtm);
        for(int i=0;i< tableMatrix.getColumnModel().getColumnCount();i++){
            tableMatrix.getColumnModel().getColumn(i).setMaxWidth(30);
        }
       
        Table renderer=new Table();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]<0){
                    renderer.addColloredCells(new Cell(i,j,new Color(255,0,0)));
                }else if (matrix[i][j]>0){
                    renderer.addColloredCells(new Cell(i,j,new Color(0,255,0)));
                }else{
                    renderer.addColloredCells(new Cell(i,j,new Color(255,255,255)));
                }
                
                tableMatrix.getModel().setValueAt(matrix[i][j], i, j);
            }
        }
          this.tableMatrix.setDefaultRenderer(this.tableMatrix.getColumnClass(1),renderer);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblColNum;
    private javax.swing.JLabel lblRowNum;
    private javax.swing.JTable tableMatrix;
    // End of variables declaration//GEN-END:variables
}
