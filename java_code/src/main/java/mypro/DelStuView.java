package mypro;

import entity.StuDO;
import imple.StuImpl;
import service.Student;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class DelStuView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel idLabel = new JLabel("学生ID");
    JTextField idTxt = new JTextField();

    JButton deleteButton = new JButton("删除");

    public DelStuView(Menu menu){
        super(menu,"删除学生",true);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);


        deleteButton.addActionListener(e -> {
            Student student = new StuImpl();
            StuDO stuDO = this.buildStuDO();
            boolean res = student.delete(stuDO);
            if(res){
                // 重新加载表格
                menu.reloadTable();
                JOptionPane.showMessageDialog(null, "success！");
            }
            else{
                JOptionPane.showMessageDialog(null, "wrong！");
            }
        });

        jPanel.add(deleteButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        setSize(350,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public StuDO buildStuDO() {
        StuDO stuDO = new StuDO();
        stuDO.setId(Integer.valueOf(idTxt.getText()));
        return stuDO;
    }

    public static void main(String[] args) {

       new DelStuView(new Menu());
    }
}
