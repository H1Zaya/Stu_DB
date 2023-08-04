package mypro;

// import test.StudentCellRender;
// import test.StudentTableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainTable extends JTable {
    public MainTable() {

        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.black);
        // set the graph self
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.MAGENTA);
        setGridColor(Color.black);
        // set the height of row
        setRowHeight(30);
        // set multi choice with ctrl
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

//    public void setDataModel(TableModel tableModel) {
//        this.setModel(tableModel);
//    }

    public void renderRule() {
        Vector<String> columns = TableModel.getColumns();
        TableCellRender render = new TableCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = new TableColumn();
            column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i == 0) {
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
            }
            // set the width is not resizable
            column.setResizable(false);
        }
    }
}
