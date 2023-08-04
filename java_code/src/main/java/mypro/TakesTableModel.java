package mypro;

//import test.StudentTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TakesTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    private static TakesTableModel tableModel = new TakesTableModel();

    static {
        columns.addElement("编号");
        columns.addElement("课程学年");
        columns.addElement("学期");
        columns.addElement("课程号");
        columns.addElement("课序号");
        columns.addElement("GPA");
    }

    private TakesTableModel() {
        super(null, columns);
    }

    public static TakesTableModel assembleModel(Vector<Vector<Object>> data) {
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