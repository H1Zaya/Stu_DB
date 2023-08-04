package mypro;

import entity.CourseDO;
import imple.CourseImpl;
import service.Course;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class CourseUpdateView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("课程ID(必填):");
    JTextField idTxt = new JTextField();

    JLabel nameLabel = new JLabel("课程名称:");
    JTextField nameTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年(必填):");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋)(必填):");
    JTextField sTxt = new JTextField();

    JLabel creditLabel = new JLabel("学分:");
    JTextField creditTxt = new JTextField();


    JLabel examLabel = new JLabel("考核方式:");
    JTextField examTxt = new JTextField();

    JLabel depLabel = new JLabel("学院:");
    JTextField depTxt = new JTextField();

    JButton insertButton = new JButton("Update");

    public CourseUpdateView(CourseMenu courseMenu){
        super(courseMenu,"更改课程",true);

        idLabel.setPreferredSize(new Dimension(100,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);

        yLabel.setPreferredSize(new Dimension(100,30));
        jPanel.add(yLabel);
        yTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(yTxt);

        sLabel.setPreferredSize(new Dimension(100,30));
        jPanel.add(sLabel);
        sTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);

        examLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(examLabel);
        examTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(examTxt);
        creditLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(creditLabel);
        creditTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(creditTxt);


        depLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(depLabel);
        depTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(depTxt);

        insertButton.addActionListener(e -> {
            Course course = new CourseImpl();
            CourseDO courseDO = this.buildCourseDO();
            boolean res = course.update(courseDO);
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

       new CourseUpdateView(new CourseMenu());
    }
}
