package mypro;

import imple.StuImpl;
import req.StudentRequest;
import res.TableDTO;
import service.Student;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.util.Vector;


public class MainView extends JFrame {

    JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // 第一个panel上的东西
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton = new JButton("删除");
    JTextField searchText = new JTextField(15);
    JButton searchButton = new JButton("查询");

    // the table in mid



    JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("<<");
    JButton nextButton = new JButton(">>");

    MainTable mainTable = new MainTable();
    private int pageNow = 1;
    private int pageSize = 10;

    public MainView(){
        super("登陆成功");
        Container contentPane = getContentPane();

        panel_1.add(addButton);
        panel_1.add(delButton);
        panel_1.add(updateButton);
        panel_1.add(searchText);
        panel_1.add(searchButton);
        contentPane.add(panel_1,BorderLayout.NORTH);

        layoutCenter(contentPane);

        panel_2.add(preButton);
        panel_2.add(nextButton);
        contentPane.add(panel_2,BorderLayout.SOUTH);
        // 自定义图标

        //
        // 大小填满全屏
//        setBounds(Dimension.getBounds());
        // 设置无缝隙
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
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchText.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        Vector<Vector<Object>> data = tableDTO.getData();

        TableModel tableModel = TableModel.assembleModel(data);
        mainTable.setModel(tableModel);
        mainTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    public static void main(String[] args){
        new MainView();
    }


}
