package ui.diary;

import domain.diary.controller.DiaryController;
import domain.diary.model.Diary;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FrameDiaryEdit extends JFrame {

    private final DiaryController controller;
    private final LocalDate diaryDate;

    private final JTextField titleField = new JTextField();
    private final JTextField weatherField = new JTextField();
    private final JTextArea contentArea = new JTextArea();

    public FrameDiaryEdit(DiaryController controller, LocalDate diaryDate) {
        this.controller = controller;
        this.diaryDate = diaryDate;

        setTitle("일기 수정");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Diary diary = controller.getByDate(diaryDate);
        if (diary == null) {
            JOptionPane.showMessageDialog(this, "일기를 불러올 수 없습니다.");
            dispose();
            return;
        }

        titleField.setText(diary.getTitle());
        weatherField.setText(diary.getWeather());
        contentArea.setText(diary.getContent());

        titleField.setBorder(BorderFactory.createTitledBorder("제목"));
        weatherField.setBorder(BorderFactory.createTitledBorder("날씨"));
        contentArea.setBorder(BorderFactory.createTitledBorder("내용"));

        JPanel fields = new JPanel(new GridLayout(2, 1));
        fields.add(titleField);
        fields.add(weatherField);

        add(new JLabel("날짜: " + diaryDate, SwingConstants.CENTER), BorderLayout.NORTH);
        add(fields, BorderLayout.WEST);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton save = new JButton("저장");
        JButton cancel = new JButton("취소");
        btnPanel.add(save);
        btnPanel.add(cancel);
        add(btnPanel, BorderLayout.SOUTH);

        save.addActionListener(e -> {
            controller.save(diaryDate, weatherField.getText(), titleField.getText(), contentArea.getText());
            JOptionPane.showMessageDialog(this, "일기가 수정되었습니다.");
            dispose();
        });

        cancel.addActionListener(e -> dispose());

        setVisible(true);
    }
}
