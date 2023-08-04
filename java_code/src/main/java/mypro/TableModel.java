package mypro;

//import test.StudentTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    private static TableModel tableModel = new TableModel();

    static {
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("GPA");
        columns.addElement("总学分");
    }

    private TableModel() {
        super(null, columns);
    }

    public static TableModel assembleModel(Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columns);
        return tableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        tableModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}