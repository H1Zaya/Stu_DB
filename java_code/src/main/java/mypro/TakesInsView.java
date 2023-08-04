package mypro;


import entity.TakesDO;
import imple.TakesImpl;
import service.Takes;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class TakesInsView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("ID");
    JTextField idTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年:");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋):");
    JTextField sTxt = new JTextField();


    JLabel course_idLabel = new JLabel("课程号:");
    JTextField course_idTxt = new JTextField();

    JLabel lessonLabel = new JLabel("课序号:");
    JTextField lessonTxt = new JTextField();

    JLabel GPALabel = new JLabel("GPA:");
    JTextField GPATxt = new JTextField();

    JButton insertButton = new JButton("Insert");

    public TakesInsView(TakesMenu takesMenu){
        super(takesMenu,"新增takes",true);

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

        course_idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(course_idLabel);
        course_idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(course_idTxt);
        lessonLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(lessonLabel);
        lessonTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(lessonTxt);


        GPALabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(GPALabel);
        GPATxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(GPATxt);


        insertButton.addActionListener(e -> {
            Takes takes = new TakesImpl();
            TakesDO takesDO = this.buildTakesDO();
            boolean res = takes.add(takesDO);
            if(res){
                // 重新加载表格
                takesMenu.reloadTable();
                JOptionPane.showMessageDialog(null, "success！");
            }
            else{
                JOptionPane.showMessageDialog(null, "wrong！");
            }
        });
        jPanel.add(insertButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        setSize(350,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    public TakesDO buildTakesDO() {
        TakesDO takesDO = new TakesDO();
        takesDO.setId(Integer.parseInt(idTxt.getText()));
        takesDO.setCourse_year(yTxt.getText());
        takesDO.setSemester(sTxt.getText());
        takesDO.setCourse_id(course_idTxt.getText());
        takesDO.setLesson_id(Integer.parseInt(lessonTxt.getText()));
        takesDO.setGPA(Float.parseFloat(GPATxt.getText()));
        return takesDO;
    }

    public static void main(String[] args) {
       new TakesInsView(new TakesMenu());
    }
}
