package ui.attendance;

import domain.attendance.controller.AttendanceController;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameAttendance extends JFrame {

    private final AttendanceController controller;
    private final BirdMessageManager messageManager;
    private final JButton btnCheck;

    public FrameAttendance(AttendanceController controller, BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;

        setTitle("출석 체크");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        btnCheck = new JButton("출석 체크하기");
        btnCheck.addActionListener(e -> handleAttendance());
        add(btnCheck);

        setVisible(true);
    }

    private void handleAttendance() {
        boolean success = controller.checkAttendance(LocalDate.now());
        if (success) {
            messageManager.say("출석 완료! 오늘도 멋져요 😊");
            messageManager.speakRandom();
        } else {
            JOptionPane.showMessageDialog(this, "이미 오늘 출석을 완료했습니다!");
        }
    }
}
