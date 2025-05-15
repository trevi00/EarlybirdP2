// ui.attendance.FrameAttendance.java
package ui.attendance;

import domain.attendance.controller.AttendanceController;
import domain.bird.controller.BirdController;
import ui.message.BirdMessageManager;
import user.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class FrameAttendance extends JFrame {

    private final AttendanceController attendanceController;
    private final BirdController birdController;
    private final BirdMessageManager messageManager;

    private JButton btnCheck;

    public FrameAttendance(AttendanceController attendanceController,
                           BirdController birdController,
                           BirdMessageManager messageManager) {
        this.attendanceController = attendanceController;
        this.birdController = birdController;
        this.messageManager = messageManager;

        setTitle("출석 체크");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        btnCheck = new JButton("출석 체크하기");
        btnCheck.addActionListener(this::handleAttendance);
        add(btnCheck);

        setVisible(true);
    }

    private void handleAttendance(ActionEvent e) {
        String username = SessionManager.getCurrentUser().getUsername();
        LocalDate today = LocalDate.now();

        boolean success = attendanceController.checkAttendance(username, today);

        if (success) {
            // 포인트 추가 + 메시지 출력
            birdController.addPoint(10);
            messageManager.say("출석 완료! 오늘도 멋져요 😊");
            messageManager.speakRandom();

            // 진화 여부 확인
            if (birdController.evolveIfNeeded()) {
                messageManager.say("🎉 축하합니다! 새가 성장했습니다!");
            }

            JOptionPane.showMessageDialog(this, "출석이 완료되었습니다!");
        } else {
            JOptionPane.showMessageDialog(this, "이미 출석했습니다!");
        }
    }
}
