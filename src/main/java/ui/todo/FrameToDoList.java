package ui.todo;

import domain.todo.controller.ToDoController;
import domain.todo.model.ToDo;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrameToDoList extends JFrame {

    private final ToDoController controller;
    private final BirdMessageManager messageManager;
    private final DefaultListModel<ToDo> listModel = new DefaultListModel<>();
    private final JList<ToDo> toDoList = new JList<>(listModel);

    public FrameToDoList(ToDoController controller, BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;

        setTitle("할 일 목록");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        toDoList.setCellRenderer(new ToDoCellRenderer());

        JPanel buttonPanel = new JPanel();
        JButton deleteBtn = new JButton("🗑 삭제");
        JButton doneBtn = new JButton("✅ 완료");

        deleteBtn.addActionListener(e -> deleteSelected());
        doneBtn.addActionListener(e -> markDone());

        buttonPanel.add(deleteBtn);
        buttonPanel.add(doneBtn);

        add(new JScrollPane(toDoList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadData();
        setVisible(true);
    }

    private void loadData() {
        listModel.clear();
        List<ToDo> list = controller.getAll();
        list.forEach(listModel::addElement);
    }

    private void deleteSelected() {
        ToDo selected = toDoList.getSelectedValue();
        if (selected == null) return;
        controller.delete(selected.getDate());
        messageManager.say("할 일을 정리했어요!");
        loadData();
    }

    private void markDone() {
        ToDo selected = toDoList.getSelectedValue();
        if (selected == null) return;
        controller.markDone(selected.getDate());
        messageManager.say("할 일을 완료했군요! 굿!");
        loadData();
    }

    private static class ToDoCellRenderer extends JLabel implements ListCellRenderer<ToDo> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public Component getListCellRendererComponent(JList<? extends ToDo> list, ToDo value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            String status = value.isDone() ? "[완료] " : "";
            setText("📅 " + value.getDate().format(formatter) + " - " + status + value.getTitle());
            setOpaque(true);
            setBackground(isSelected ? new Color(200, 230, 255) : Color.WHITE);
            setForeground(Color.DARK_GRAY);
            setFont(new Font("맑은 고딕", Font.PLAIN, 13));
            return this;
        }
    }
}
