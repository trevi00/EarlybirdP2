// ui.todo.FrameToDoCreate.java
package ui.todo;

import domain.todo.controller.ToDoController;
import ui.message.BirdMessageManager;

import javax.swing.*;
import java.awt.*;

/**
 * 오늘의 할 일 작성 화면 (Controller 기반)
 */
public class FrameToDoCreate extends JFrame {

    private final ToDoController toDoController;
    private final BirdMessageManager messageManager;

    public FrameToDoCreate(ToDoController toDoController, BirdMessageManager messageManager) {
        this.toDoController = toDoController;
        this.messageManager = messageManager;

        setTitle("오늘의 할 일 작성");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));

        JTextField titleField = new JTextField();
        JTextArea contentArea = new JTextArea();
        contentArea.setLineWrap(true);

        inputPanel.add(new JLabel("제목:"), BorderLayout.NORTH);
        inputPanel.add(titleField, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.add(new JLabel("내용:"), BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JButton btnSave = new JButton("저장하기");
        btnSave.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();

            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력해주세요.");
                return;
            }

            boolean saved = toDoController.saveToday(title, content);
            if (saved) {
                messageManager.say("오늘 할 일이 등록되었어요!");
                messageManager.speakRandom();
                JOptionPane.showMessageDialog(this, "할 일이 저장되었습니다! 포인트 +10 🎉");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "오늘은 이미 할 일을 작성하셨어요!");
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);
    }
}
