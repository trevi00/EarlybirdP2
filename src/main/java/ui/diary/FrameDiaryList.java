// ui.diary.FrameDiaryList.java
package ui.diary;

import domain.diary.controller.DiaryController;
import domain.diary.model.Diary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrameDiaryList extends JFrame {

    private final DiaryController diaryController;
    private final JTable diaryTable;
    private final DefaultTableModel tableModel;

    public FrameDiaryList(DiaryController diaryController) {
        this.diaryController = diaryController;

        setTitle("나의 일기 목록");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new String[]{"날짜", "제목"}, 0);
        diaryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(diaryTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnEdit = new JButton("수정");
        JButton btnDelete = new JButton("삭제");

        btnEdit.addActionListener(e -> {
            int row = diaryTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "수정할 일기를 선택해주세요.");
                return;
            }
            String dateStr = (String) diaryTable.getValueAt(row, 0);
            LocalDate date = LocalDate.parse(dateStr);
            new FrameDiaryEdit(diaryController, date);
        });

        btnDelete.addActionListener(e -> {
            int row = diaryTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "삭제할 일기를 선택해주세요.");
                return;
            }

            String dateStr = (String) diaryTable.getValueAt(row, 0);
            LocalDate date = LocalDate.parse(dateStr);
            int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                diaryController.delete(date);
                loadDiaryList(); // 목록 갱신
            }
        });

        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        loadDiaryList();
        setVisible(true);
    }

    private void loadDiaryList() {
        tableModel.setRowCount(0);
        List<Diary> diaryList = diaryController.getAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Diary diary : diaryList) {
            tableModel.addRow(new Object[]{
                    diary.getDate().format(formatter),
                    diary.getTitle()
            });
        }
    }
}
