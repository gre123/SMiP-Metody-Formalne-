package smip.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Tomek
 */
public class Table extends DefaultTableCellRenderer {

    private final ArrayList<Cell> colloredCell = new ArrayList<>();

    public void addColloredCells(int[][] matrix) {
        
        
    }

    public void addColloredCells(Cell cell) {
        colloredCell.add(cell);
    }

    public void cleanColloredCells() {
        colloredCell.clear();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        Component c = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus,
                row, column);
        c.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < colloredCell.size(); i++) {
            if (colloredCell.get(i).x == column && colloredCell.get(i).y == row) {
                c.setBackground(colloredCell.get(i).color);
            }
        }
        return c;
    }
}
