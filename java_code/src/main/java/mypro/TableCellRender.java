package mypro;

//import test.StudentCellRender;
//import test.StudentTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableCellRender extends DefaultTableCellRenderer {
    // run before every column and row appear
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // according to the row number of each row set the different color for them
        if (row % 2 == 0) {
            setBackground(Color.white);
        } else {
            setBackground(Color.lightGray);
        }
        // set the data show in mid
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}