package mypro;

//import test.StudentTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class LessonTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    private static LessonTableModel tableModel = new LessonTableModel();

    static {
        columns.addElement("课程号");
        columns.addElement("课程学年");
        columns.addElement("学期");
        columns.addElement("课序号");
        columns.addElement("任课教师");
        columns.addElement("天数");
        columns.addElement("教学楼");
        columns.addElement("房间号");
    }

    private LessonTableModel() {
        super(null, columns);
    }

    public static LessonTableModel assembleModel(Vector<Vector<Object>> data) {
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