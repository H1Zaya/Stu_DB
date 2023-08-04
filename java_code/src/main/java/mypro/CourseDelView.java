package mypro;

import entity.CourseDO;
import imple.CourseImpl;
import service.Course;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class CourseDelView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("课程ID");
    JTextField idTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年:");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋):");
    JTextField sTxt = new JTextField();


    JButton insertButton = new JButton("Delete");

    public CourseDelView(CourseMenu courseMenu){
        super(courseMenu,"删除课程",true);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);

        yLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(yLabel);
        yTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(yTxt);

        sLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sLabel);
        sTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sTxt);


        insertButton.addActionListener(e -> {
            Course course = new CourseImpl();
            CourseDO courseDO = this.buildCourseDO();
            boolean res = course.delete(courseDO);
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



        setSize(350,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    public CourseDO buildCourseDO() {
        CourseDO courseDO = new CourseDO();
        courseDO.setCourse_id(idTxt.getText());
        courseDO.setCourse_year(yTxt.getText());
        courseDO.setSemester(sTxt.getText());

        return courseDO;
    }

    public static void main(String[] args) {

       new CourseDelView(new CourseMenu());
    }
}
