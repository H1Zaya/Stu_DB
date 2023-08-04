package mypro;

import entity.LessonDO;
import imple.LessonImpl;
import service.Lesson;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class LessonDelView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("课程ID");
    JTextField idTxt = new JTextField();

    JLabel yLabel = new JLabel("课程学年:");
    JTextField yTxt = new JTextField();

    JLabel sLabel = new JLabel("学期(春/秋):");
    JTextField sTxt = new JTextField();

    JLabel lesLabel = new JLabel("课序号:");
    JTextField lesTxt = new JTextField();




    JButton insertButton = new JButton("Delete");

    public LessonDelView(LessonMenu lessonMenu){
        super(lessonMenu,"删除lesson",true);

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

        lesLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(lesLabel);
        lesTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(lesTxt);





        insertButton.addActionListener(e -> {
            Lesson lesson = new LessonImpl();
            LessonDO lessonDO = this.buildLessonDO();
            boolean res = lesson.delete(lessonDO);
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

        setSize(350,340);
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

        return lessonDO;
    }

    public static void main(String[] args) {

       new LessonDelView(new LessonMenu());
    }
}
