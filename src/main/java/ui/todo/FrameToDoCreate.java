package ui.todo;

import domain.todo.controller.ToDoController;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameToDoCreate extends JFrame {

    private final ToDoController controller;
    private final BirdMessageManager messageManager;

    private final JTextField titleField = new JTextField();
    private final JTextArea contentArea = new JTextArea();

    public FrameToDoCreate(ToDoController controller, BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;

        setTitle("오늘의 할 일 작성");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        titleField.setBorder(BorderFactory.createTitledBorder("제목"));
        contentArea.setBorder(BorderFactory.createTitledBorder("내용"));
        contentArea.setLineWrap(true);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleField, BorderLayout.NORTH);
        topPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JButton saveBtn = new JButton("저장하기");
        saveBtn.addActionListener(e -> save());

        add(topPanel, BorderLayout.CENTER);
        add(saveBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void save() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력해주세요.");
            return;
        }

        boolean success = controller.createToday(title, content);

        if (success) {
            JOptionPane.showMessageDialog(this, "할 일이 저장되었습니다! 포인트 +10");
            messageManager.say("오늘의 할 일을 멋지게 등록했어요!");
            messageManager.speakRandom();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "오늘은 이미 할 일을 작성하셨습니다.");
        }
    }
}
