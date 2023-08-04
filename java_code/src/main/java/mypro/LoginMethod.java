package mypro;

import entity.AdminDO;
import imple.AdminImp;
import service.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginMethod extends KeyAdapter implements ActionListener {

    private LoginView loginView;

    public LoginMethod(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("Log in".equals(text)) {

            login();
        }
    }

    private void login() {
        // 放置输入检测

        // 正确流程
//        System.out.println("登录");
        String user = loginView.getUserTxt().getText();
        char[] chars = loginView.getPasswordField().getPassword();
        if (user == null || "".equals(user.trim()) || chars == null) {
            JOptionPane.showMessageDialog(loginView, "用户名密码不能为空");
            return;
        }
        String pwd = new String(chars);

        System.out.println(user + ':' + pwd);

        Admin admin = new AdminImp();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(user);
        adminDO.setPwd(pwd);
        boolean flag = admin.validAdmin(adminDO);

        //可以查询数据库
        if (flag) {
//            new MainView();
            new Menu();
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "用户名或密码错误,请重新输入.");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            login();
        }
    }


}
