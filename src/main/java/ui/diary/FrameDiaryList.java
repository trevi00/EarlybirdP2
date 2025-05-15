package ui.diary;

import domain.diary.controller.DiaryController;
import domain.diary.model.Diary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class FrameDiaryList extends JFrame {

    private final DiaryController controller;
    private final JTable diaryTable;
    private final DefaultTableModel tableModel;

    public FrameDiaryList(DiaryController controller) {
        this.controller = controller;

        setTitle("일기 목록");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new String[]{"날짜", "제목"}, 0);
        diaryTable = new JTable(tableModel);
        add(new JScrollPane(diaryTable), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton edit = new JButton("수정");
        JButton delete = new JButton("삭제");
        btnPanel.add(edit);
        btnPanel.add(delete);
        add(btnPanel, BorderLayout.SOUTH);

        edit.addActionListener(e -> {
            int row = diaryTable.getSelectedRow();
            if (row == -1) return;
            LocalDate date = LocalDate.parse((String) diaryTable.getValueAt(row, 0));
            new FrameDiaryEdit(controller, date);
        });

        delete.addActionListener(e -> {
            int row = diaryTable.getSelectedRow();
            if (row == -1) return;
            LocalDate date = LocalDate.parse((String) diaryTable.getValueAt(row, 0));
            controller.delete(date);
            loadData(); // 갱신
        });

        loadData();
        setVisible(true);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Diary> list = controller.getAll();
        for (Diary d : list) {
            tableModel.addRow(new Object[]{d.getDate().toString(), d.getTitle()});
        }
    }
}
