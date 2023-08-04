package mypro;

import imple.StuCourseImpl;
import imple.StuImpl;
import req.StudentRequest;
import res.TableDTO;
import service.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class StuCourseMenu extends JFrame implements ActionListener {

    JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JTextField searchText = new JTextField(15);
    JButton searchButton = new JButton("按学生编号查询");

    //
    MainTable mainTable = new MainTable();


    public StuCourseMenu() {
        super("stu_course");
        JMenuBar jmb = new JMenuBar();
        //不能设定位置，会自动放在最上部
        this.setJMenuBar(jmb);
        //添加菜单
        JMenu menu1 = new JMenu("表格");

        JMenuItem item1 = new JMenuItem("student");
        JMenuItem item2 = new JMenuItem("course");
        JMenuItem item3 = new JMenuItem("lesson");
        JMenuItem item4 = new JMenuItem("takes");
        JMenuItem item5 = new JMenuItem("stu_course");

        //添加菜单项至菜单上
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.add(item5);


        //将菜单加入至菜单条
        jmb.add(menu1);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);


        Container contentPane = getContentPane();


        panel_1.add(searchText);
        panel_1.add(searchButton);
        contentPane.add(panel_1, BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            reloadTable();
        });
//
        layoutCenter(contentPane);

        setBounds(Dimension.getBounds());
//         设置无缝隙
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StuCourseMenu();
    }

    private void layoutCenter(Container contentPane) {

        StuCourseImpl stuCourse = new StuCourseImpl();
        StudentRequest request = new StudentRequest();

        TableDTO tableDTO = stuCourse.retrieveStuCourse(request);
        Vector<Vector<Object>> data = tableDTO.getData();

        StuCourseTableModel tableModel = StuCourseTableModel.assembleModel(data);
        mainTable.setModel(tableModel);

        JScrollPane jScrollPane = new JScrollPane(mainTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    public void reloadTable() {
        StuCourseImpl stuCourse = new StuCourseImpl();
        StudentRequest request = new StudentRequest();
        request.setSearchKey(searchText.getText());
        TableDTO tableDTO = stuCourse.retrieveStuCourse(request);
        Vector<Vector<Object>> data = tableDTO.getData();

        StuCourseTableModel.updateModel(data);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if ("student".equals(str)) {
            System.out.println("student正在被点击");
            new Menu();
            dispose();
        } else if ("course".equals(str)) {
            System.out.println("course正在被点击");
            new CourseMenu();
            dispose();
        } else if ("lesson".equals(str)) {
            System.out.println("lesson正在被点击");
            new LessonMenu();
            dispose();
        } else if ("takes".equals(str)) {
            System.out.println("takes正在被点击");
            new TakesMenu();
            dispose();
        } else if ("stu_course".equals(str)) {
            System.out.println("stu_course正在被点击");
        }
    }
}

