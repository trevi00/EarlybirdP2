// ui.todo.FrameToDoList.java
package ui.todo;

import domain.todo.controller.ToDoController;
import domain.todo.model.ToDo;
import ui.message.BirdMessageManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrameToDoList extends JFrame {

    private final ToDoController toDoController;
    private final BirdMessageManager messageManager;
    private final DefaultListModel<ToDo> listModel;

    public FrameToDoList(ToDoController toDoController, BirdMessageManager messageManager) {
        this.toDoController = toDoController;
        this.messageManager = messageManager;
        this.listModel = new DefaultListModel<>();

        setTitle("할 일 목록");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        initUI();
        loadData();

        setVisible(true);
    }

    private void initUI() {
        JList<ToDo> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new ToDoListCellRenderer());

        JButton btnDelete = new JButton("🗑 삭제하기");
        JButton btnDone = new JButton("✅ 완료 표시");

        btnDelete.addActionListener(e -> {
            ToDo selected = list.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "삭제할 항목을 선택해주세요.");
                return;
            }
            toDoController.delete(selected.getDate());
            listModel.removeElement(selected);
            messageManager.say("할 일을 잘 정리했어요!");
        });

        btnDone.addActionListener(e -> {
            ToDo selected = list.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "완료할 항목을 선택해주세요.");
                return;
            }
            toDoController.markAsDone(selected.getDate());
            messageManager.say("할 일을 마무리했군요! 잘했어요!");
            reload();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnDone);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        listModel.clear();
        List<ToDo> list = toDoController.getAll();
        list.forEach(listModel::addElement);
    }

    private void reload() {
        loadData();
    }

    private static class ToDoListCellRenderer extends JLabel implements ListCellRenderer<ToDo> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public Component getListCellRendererComponent(JList<? extends ToDo> list, ToDo value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText("📅 " + value.getDate().format(formatter)
                    + " - " + (value.isDone() ? "[완료] " : "") + value.getTitle());

            setOpaque(true);
            setBackground(isSelected ? new Color(200, 230, 255) : Color.WHITE);
            setForeground(Color.DARK_GRAY);
            setFont(new Font("맑은 고딕", Font.PLAIN, 13));
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            return this;
        }
    }
}
