package mypro;

import entity.CourseDO;
import entity.StuDO;
import imple.CourseImpl;
import imple.StuImpl;
import service.Course;
import service.Student;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class CourseInsertView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("课程ID:");
    JTextField idTxt = new JTextField();

    JLabel nameLabel = new JLabel("课程名称:");
    JTextField nameTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年:");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋):");
    JTextField sTxt = new JTextField();

    JLabel creditLabel = new JLabel("学分:");
    JTextField creditTxt = new JTextField();

    JLabel examLabel = new JLabel("考核方式:");
    JTextField examTxt = new JTextField();

    JLabel depLabel = new JLabel("学院:");
    JTextField depTxt = new JTextField();

    JButton insertButton = new JButton("Insert");

    public CourseInsertView(CourseMenu courseMenu){
        super(courseMenu,"新增课程",true);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);
        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);
        yLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(yLabel);
        yTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(yTxt);
        examLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(examLabel);
        examTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(examTxt);
        creditLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(creditLabel);
        creditTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(creditTxt);
        sLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sLabel);
        sTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sTxt);

        depLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(depLabel);
        depTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(depTxt);

        insertButton.addActionListener(e -> {
            Course course = new CourseImpl();
            CourseDO courseDO = this.buildCourseDO();
            boolean res = course.add(courseDO);
            if(res){
                // 重新加载表格
                courseMenu.reloadTable();
                JOptionPane.showMessageDialog(null, "success！");
            }
            else{
                JOptionPane.showMessageDialog(null, "wrong！");
            }
        });
        jPanel.add(insertButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        setSize(350,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    public CourseDO buildCourseDO() {
        CourseDO courseDO = new CourseDO();
        courseDO.setCourse_id(idTxt.getText());
        courseDO.setCourse_name(nameTxt.getText());
        courseDO.setCourse_year(yTxt.getText());
        courseDO.setSemester(sTxt.getText());
        courseDO.setCredits(Integer.parseInt(creditTxt.getText()));
        courseDO.setExam(examTxt.getText());
        courseDO.setDepartment(depTxt.getText());
        return courseDO;
    }

    public static void main(String[] args) {

       new CourseInsertView(new CourseMenu());
    }
}
