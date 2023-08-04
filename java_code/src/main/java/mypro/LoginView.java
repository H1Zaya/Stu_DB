package mypro;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class LoginView extends JFrame {

    JLabel nameLabel = new JLabel("my project",JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pLabel = new JLabel("密码:");
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Log in");
    JButton cancelButton = new JButton("refresh");

    LoginMethod loginMethod;

    public LoginView(){
        super("赵的作业");

        loginMethod = new LoginMethod(this);

        Container contentPane = getContentPane();
        //标题字体设置
        nameLabel.setFont(new Font("微软雅黑",Font.PLAIN,30));
        Dimension dimension1 = new Dimension();
        dimension1.setSize(100,10);
        nameLabel.setPreferredSize(new Dimension(200,100));
        //others
        Font smallFont = new Font("微软雅黑",Font.PLAIN,20);
        userNameLabel.setFont(smallFont);
        pLabel.setFont(smallFont);
        loginButton.setFont(smallFont);
        cancelButton.setFont(smallFont);

        //input windows size
        userTxt.setPreferredSize(new Dimension(200,30));
        passwordField.setPreferredSize(new Dimension(200,30));

        //控件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pLabel);
        centerPanel.add(passwordField);
        centerPanel.add(loginButton);
        //centerPanel.add(cancelButton);

        loginButton.addActionListener(loginMethod);
        loginButton.addKeyListener(loginMethod);

        // 弹簧布局
        // username
        Spring width_1 = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),Spring.constant(20));
        int offsetx_1 = width_1.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetx_1,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        // user txt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        // p label
        springLayout.putConstraint(SpringLayout.EAST,pLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pLabel,20,SpringLayout.SOUTH,userNameLabel);
        // passwordField
        springLayout.putConstraint(SpringLayout.WEST,passwordField,20,SpringLayout.EAST,pLabel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordField,0,SpringLayout.NORTH,pLabel);

        // login
        springLayout.putConstraint(SpringLayout.WEST,loginButton,50,SpringLayout.WEST,pLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginButton,50,SpringLayout.SOUTH,pLabel);
        //cancel
//        springLayout.putConstraint(SpringLayout.WEST,cancelButton,50,SpringLayout.EAST,loginButton);
//        springLayout.putConstraint(SpringLayout.NORTH,cancelButton,0,SpringLayout.NORTH,loginButton);


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        // 设置默认按钮为login,与绑定回车关联
        getRootPane().setDefaultButton(loginButton);


        // 改图标,延后做


        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public void setUserTxt(JTextField userTxt) {
        this.userTxt = userTxt;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
