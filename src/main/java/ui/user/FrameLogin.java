// ui.user.FrameLogin.java
package ui.user;

import domain.user.controller.UserController;
import domain.user.model.User;
import ui.main.FrameMain;
import user.session.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * 로그인 화면 (UserController 기반)
 */
public class FrameLogin extends JFrame {

    private final UserController controller;

    public FrameLogin(UserController controller) {
        this.controller = controller;

        setTitle("로그인");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton btnLogin = new JButton("로그인");
        JButton btnRegister = new JButton("회원가입");

        add(new JLabel("아이디:"));
        add(usernameField);
        add(new JLabel("비밀번호:"));
        add(passwordField);
        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            User user = controller.login(username, password);
            if (user != null) {
                SessionManager.login(user);
                JOptionPane.showMessageDialog(this, "로그인 성공");
                dispose();
                new FrameMain(); // 메인 메뉴 호출
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패. 아이디 또는 비밀번호 확인");
            }
        });

        btnRegister.addActionListener(e -> {
            new RegisterFrame(controller);
        });

        setVisible(true);
    }
}
