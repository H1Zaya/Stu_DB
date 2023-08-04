package mypro;

//import test.StudentTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class CourseTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    private static CourseTableModel tableModel = new CourseTableModel();

    static {
        columns.addElement("课程号");
        columns.addElement("课程名");
        columns.addElement("课程学年");
        columns.addElement("学期");
        columns.addElement("学分");
        columns.addElement("考察类型");
        columns.addElement("学院");
    }

    private CourseTableModel() {
        super(null, columns);
    }

    public static CourseTableModel assembleModel(Vector<Vector<Object>> data) {
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