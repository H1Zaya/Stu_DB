package mypro;

//import test.StudentTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class StuCourseTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    private static StuCourseTableModel tableModel = new StuCourseTableModel();

    static {
        columns.addElement("编号");
        columns.addElement("course_id");
        columns.addElement("course_year");
        columns.addElement("semester");
        columns.addElement("lesson_id");
        columns.addElement("学分");
    }

    private StuCourseTableModel() {
        super(null, columns);
    }

    public static StuCourseTableModel assembleModel(Vector<Vector<Object>> data) {
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