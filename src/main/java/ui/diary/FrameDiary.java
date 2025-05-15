package ui.diary;

import domain.diary.controller.DiaryController;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameDiary extends JFrame {

    private final DiaryController controller;
    private final BirdMessageManager messageManager;

    private final JTextField titleField = new JTextField();
    private final JTextArea contentArea = new JTextArea();

    public FrameDiary(DiaryController controller, BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;

        setTitle("일기 작성");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JLabel("오늘 날짜: " + LocalDate.now(), SwingConstants.CENTER), BorderLayout.NORTH);

        titleField.setBorder(BorderFactory.createTitledBorder("제목"));
        contentArea.setBorder(BorderFactory.createTitledBorder("내용"));
        contentArea.setLineWrap(true);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(titleField, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JButton saveBtn = new JButton("저장");
        saveBtn.addActionListener(e -> saveDiary());
        add(saveBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveDiary() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력해주세요.");
            return;
        }

        boolean saved = controller.save(LocalDate.now(), title, content);
        if (saved) {
            JOptionPane.showMessageDialog(this, "일기가 저장되었습니다.");
            messageManager.say("오늘 하루도 잘 기록했어요!");
            messageManager.speakRandom();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "이미 오늘 일기를 작성하셨습니다.");
        }
    }
}
