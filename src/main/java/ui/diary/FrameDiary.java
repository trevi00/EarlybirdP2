// ui.diary.FrameDiary.java
package ui.diary;

import domain.diary.controller.DiaryController;
import ui.message.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameDiary extends JFrame {

    private final DiaryController diaryController;
    private final BirdMessageManager messageManager;

    private JTextField titleField;
    private JTextArea contentArea;

    public FrameDiary(DiaryController diaryController, BirdMessageManager messageManager) {
        this.diaryController = diaryController;
        this.messageManager = messageManager;

        setTitle("일기 작성");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JLabel dateLabel = new JLabel("오늘 날짜: " + LocalDate.now());
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(dateLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        titleField = new JTextField();
        titleField.setBorder(BorderFactory.createTitledBorder("제목"));
        centerPanel.add(titleField, BorderLayout.NORTH);

        contentArea = new JTextArea();
        contentArea.setBorder(BorderFactory.createTitledBorder("내용"));
        centerPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JButton btnSave = new JButton("일기 저장");
        btnSave.addActionListener(e -> saveDiary());
        add(btnSave, BorderLayout.SOUTH);
    }

    private void saveDiary() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력해주세요.");
            return;
        }

        diaryController.saveWithWeather(LocalDate.now(), title, content);

        messageManager.say("오늘 하루도 잘 기록했어요 😊");
        messageManager.speakRandom();

        JOptionPane.showMessageDialog(this, "일기가 저장되었습니다!");
        dispose();
    }
}
