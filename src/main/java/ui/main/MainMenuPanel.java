// ui.main.MainMenuPanel.java
package ui.main;

import app.context.EarlyBirdContext;
import domain.attendance.controller.AttendanceController;
import domain.bird.controller.BirdController;
import domain.todo.controller.ToDoController;
import domain.weather.controller.WeatherController;
import ui.attendance.FrameAttendance;
import ui.attendance.FrameAttendanceStats;
import ui.bird.FrameBird;
import ui.message.BirdMessageDisplayer;
import ui.message.BirdMessageManager;
import ui.message.SwingBirdMessageDisplayer;
import ui.todo.FrameToDoCreate;
import ui.todo.FrameToDoList;
import ui.weather.FrameWeatherView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenuPanel extends JPanel {

    private final EarlyBirdContext context;
    private final BirdMessageManager messageManager;

    public MainMenuPanel(JFrame parentFrame, EarlyBirdContext context) {
        this.context = context;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        add(Box.createVerticalStrut(30)); // 상단 여백

        // 🐦 새 메시지 매니저 초기화 (Displayer 주입)
        ImageIcon birdIcon = new ImageIcon(getClass().getClassLoader().getResource("img/bird_icon.png"));
        BirdMessageDisplayer displayer = new SwingBirdMessageDisplayer(parentFrame, birdIcon);
        context.birdMessageManager.setDisplayer(displayer); // displayer 주입
        this.messageManager = context.birdMessageManager;

        // 출석 체크
        add(createButton("출석 체크", "attendance_icon.png", () ->
                new FrameAttendance(
                        context.attendanceController,
                        context.birdController,
                        messageManager
                )
        ));

        add(Box.createVerticalStrut(20));

        // 출석 통계
        add(createButton("출석 통계 보기", "stats_icon.png", () ->
                new FrameAttendanceStats(context.attendanceController)
        ));

        add(Box.createVerticalStrut(20));

        // 할 일 작성
        add(createButton("오늘 할 일 작성", "todo_icon.png", () ->
                new FrameToDoCreate(context.toDoController, messageManager)
        ));

        add(Box.createVerticalStrut(20));

        // 할 일 목록
        add(createButton("할 일 목록 보기", "todo_list_icon.png", () ->
                new FrameToDoList(context.toDoController, messageManager)
        ));

        add(Box.createVerticalStrut(20));

        // 날씨 보기
        add(createButton("날씨 확인", "weather_icon.png", () ->
                new FrameWeatherView(context.weatherController, messageManager)
        ));

        add(Box.createVerticalStrut(20));

        // 새 상태 보기
        add(createButton("새 보기", "bird_icon.png", () ->
                new FrameBird(context.birdController, messageManager)
        ));

        add(Box.createVerticalGlue());
    }

    private JButton createButton(String text, String iconName, Runnable action) {
        JButton button = new JButton(text, loadIcon(iconName));
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 50));
        button.setBackground(new Color(135, 206, 250));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> action.run());
        return button;
    }

    private ImageIcon loadIcon(String name) {
        URL resource = getClass().getClassLoader().getResource("img/" + name);
        if (resource == null) {
            System.err.println("⚠️ 아이콘 없음: " + name);
            return new ImageIcon();
        }
        Image img = new ImageIcon(resource).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
