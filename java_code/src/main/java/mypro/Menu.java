package mypro;

import imple.StuImpl;
import req.StudentRequest;
import res.TableDTO;
import service.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class Menu extends JFrame implements ActionListener {

    JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JTextField searchText = new JTextField(15);
    JButton searchButton = new JButton("按编号查询");

//
//    // the table in mid
    MainTable mainTable = new MainTable();


    public Menu(){
        super("student");
        JMenuBar jmb = new JMenuBar();
        //不能设定位置，会自动放在最上部
        this.setJMenuBar(jmb);
        //添加菜单
        JMenu menu1 = new JMenu("表格");
        JMenu menu2 = new JMenu("Edit");

        JMenuItem item1 = new JMenuItem("student");
        JMenuItem item2 = new JMenuItem("course");
        JMenuItem item3 = new JMenuItem("lesson");
        JMenuItem item4 = new JMenuItem("takes");
        JMenuItem item5 = new JMenuItem("stu_course");
        JMenuItem item6 = new JMenuItem("insert");
        JMenuItem item7 = new JMenuItem("delete");
        JMenuItem item8 = new JMenuItem("alter");
        //添加菜单项至菜单上
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        menu2.add(item8);

        //将菜单加入至菜单条
        jmb.add(menu1);
        jmb.add(menu2);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);

        Container contentPane = getContentPane();


        panel_1.add(searchText);
        panel_1.add(searchButton);
        contentPane.add(panel_1,BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            reloadTable();
        });
//
        layoutCenter(contentPane);
//

        setBounds(Dimension.getBounds());
//         设置无缝隙
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter(Container contentPane){

        Student studentService = new StuImpl();
        StudentRequest request = new StudentRequest();

        TableDTO tableDTO = studentService.retrieveStudents(request);
        Vector<Vector<Object>> data = tableDTO.getData();

        TableModel tableModel = TableModel.assembleModel(data);
        mainTable.setModel(tableModel);
        mainTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }


    public static void main(String[] args){
        new Menu();
    }

    public void reloadTable() {
        Student studentService = new StuImpl();
        StudentRequest request = new StudentRequest();
        request.setSearchKey(searchText.getText());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        Vector<Vector<Object>> data = tableDTO.getData();

        TableModel.updateModel(data);
        mainTable.renderRule();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if("student".equals(str)) {
            System.out.println("student正在被点击");
        }
        else if("course".equals(str)){
            System.out.println("course正在被点击");
            new CourseMenu();
            dispose();
        }
        else if("lesson".equals(str)){
            System.out.println("lesson正在被点击");
            new LessonMenu();
            dispose();
        }
        else if("takes".equals(str)){
            System.out.println("takes正在被点击");
            new TakesMenu();
            dispose();
        }
        else if("stu_course".equals(str)){
            System.out.println("stu_course正在被点击");
            new StuCourseMenu();
            dispose();
        }
        else if("insert".equals(str)){
            System.out.println("insert正在被点击");
            new InsertView(this);
        }
        else if("delete".equals(str)){
            System.out.println("delete正在被点击");
            new DelStuView(this);
        }
        else{
            System.out.println("alter正在被点击");
            new UpdateStuView(this);
        }
    }
}
