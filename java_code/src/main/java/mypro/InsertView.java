package mypro;

import entity.StuDO;
import imple.StuImpl;
import service.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class InsertView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("学生ID");
    JTextField idTxt = new JTextField();

    JLabel nameLabel = new JLabel("学生姓名:");
    JTextField nameTxt = new JTextField();

    JLabel GLabel = new JLabel("GPA:");
    JTextField GTxt = new JTextField();

    JLabel pLabel = new JLabel("密码:");
    JTextField pwdTxt = new JTextField();

    JLabel creditLabel = new JLabel("总学分:");
    JTextField creditTxt = new JTextField();

    JButton insertButton = new JButton("Insert");

    public InsertView(Menu menu){
        super(menu,"新增学生",true);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);
        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);
        GLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(GLabel);
        GTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(GTxt);
        creditLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(creditLabel);
        creditTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(creditTxt);
        pLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(pLabel);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(pwdTxt);

        insertButton.addActionListener(e -> {
            Student student = new StuImpl();
            StuDO stuDO = this.buildStuDO();
            boolean res = student.add(stuDO);
            if(res){
                // 重新加载表格
                menu.reloadTable();
                JOptionPane.showMessageDialog(null, "success！");
            }
            else{
                JOptionPane.showMessageDialog(null, "wrong！");
            }
        });
        jPanel.add(insertButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        setSize(350,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }



    public StuDO buildStuDO() {
        StuDO stuDO = new StuDO();
        stuDO.setId(Integer.valueOf(idTxt.getText()));
        stuDO.setStudentName(nameTxt.getText());
        stuDO.setGpa(Float.parseFloat(GTxt.getText()));
        stuDO.setCredits(Integer.valueOf(creditTxt.getText()));
        stuDO.setPwd(pwdTxt.getText());
        return stuDO;
    }

    public static void main(String[] args) {

       new InsertView(new Menu());
    }
}
