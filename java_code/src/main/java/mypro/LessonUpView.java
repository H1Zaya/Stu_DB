package mypro;

import entity.LessonDO;
import imple.LessonImpl;
import service.Lesson;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class LessonUpView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("课程ID(存在):");
    JTextField idTxt = new JTextField();

    JLabel teachLabel = new JLabel("任课教师:");
    JTextField teachTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年(存在):");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋)(存在):");
    JTextField sTxt = new JTextField();

    JLabel lesLabel = new JLabel("课序号(存在):");
    JTextField lesTxt = new JTextField();

    JLabel dayLabel = new JLabel("天数:");
    JTextField dayTxt = new JTextField();


    JLabel buildLabel = new JLabel("教学楼:");
    JTextField buildTxt = new JTextField();

    JLabel roomLabel = new JLabel("房间号:");
    JTextField roomTxt = new JTextField();


    JButton insertButton = new JButton("Update");

    public LessonUpView(LessonMenu lessonMenu){
        super(lessonMenu,"新增lesson",true);

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

        lesLabel.setPreferredSize(new Dimension(100,30));
        jPanel.add(lesLabel);
        lesTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(lesTxt);


        dayLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(dayLabel);
        dayTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(dayTxt);




        teachLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(teachLabel);
        teachTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(teachTxt);

        buildLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(buildLabel);
        buildTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(buildTxt);

        roomLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(roomLabel);
        roomTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(roomTxt);

        insertButton.addActionListener(e -> {
            Lesson lesson = new LessonImpl();
            LessonDO lessonDO = this.buildLessonDO();

            boolean res = lesson.update(lessonDO);

            if(res){
                // 重新加载表格
                lessonMenu.reloadTable();
                JOptionPane.showMessageDialog(null, "success！");
            }
            else{
                JOptionPane.showMessageDialog(null, "wrong！");
            }
        });
        jPanel.add(insertButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        setSize(350,540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    public LessonDO buildLessonDO() {
        LessonDO lessonDO = new LessonDO();
        lessonDO.setCourse_id(idTxt.getText());
        lessonDO.setCourse_year(yTxt.getText());
        lessonDO.setSemester(sTxt.getText());
        lessonDO.setLesson_id(Integer.parseInt(lesTxt.getText()));
        lessonDO.setTeacher(teachTxt.getText());
        lessonDO.setDays(dayTxt.getText());
        lessonDO.setBuilding(buildTxt.getText());
        lessonDO.setRoom_no(Integer.parseInt(roomTxt.getText()));
        return lessonDO;
    }

    public static void main(String[] args) {

       new LessonUpView(new LessonMenu());
    }
}
