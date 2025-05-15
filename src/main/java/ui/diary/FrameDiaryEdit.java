// ui.diary.FrameDiaryEdit.java
package ui.diary;

import domain.diary.controller.DiaryController;
import domain.diary.model.Diary;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameDiaryEdit extends JFrame {

    private final DiaryController diaryController;
    private final LocalDate diaryDate;

    private JTextField titleField;
    private JTextField weatherField;
    private JTextArea contentArea;

    public FrameDiaryEdit(DiaryController diaryController, LocalDate diaryDate) {
        this.diaryController = diaryController;
        this.diaryDate = diaryDate;

        setTitle("일기 수정");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        loadDiaryContent();
        setVisible(true);
    }

    private void initUI() {
        JLabel dateLabel = new JLabel("날짜: " + diaryDate, SwingConstants.CENTER);
        dateLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(dateLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());

        titleField = new JTextField();
        titleField.setBorder(BorderFactory.createTitledBorder("제목"));
        inputPanel.add(titleField, BorderLayout.NORTH);

        weatherField = new JTextField();
        weatherField.setBorder(BorderFactory.createTitledBorder("날씨"));
        inputPanel.add(weatherField, BorderLayout.CENTER);

        contentArea = new JTextArea();
        contentArea.setBorder(BorderFactory.createTitledBorder("내용"));
        add(new JScrollPane(contentArea), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("저장");
        JButton btnCancel = new JButton("취소");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            String title = titleField.getText().trim();
            String weather = weatherField.getText().trim();
            String content = contentArea.getText().trim();

            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력해주세요.");
                return;
            }

            diaryController.save(diaryDate, weather, title, content);
            JOptionPane.showMessageDialog(this, "일기가 수정되었습니다.");
            dispose();
        });

        btnCancel.addActionListener(e -> dispose());
    }

    private void loadDiaryContent() {
        Diary diary = diaryController.get(diaryDate);
        if (diary == null) {
            JOptionPane.showMessageDialog(this, "일기 내용을 불러올 수 없습니다.");
            dispose();
            return;
        }

        titleField.setText(diary.getTitle());
        weatherField.setText(diary.getWeather());
        contentArea.setText(diary.getContent());
    }
}
