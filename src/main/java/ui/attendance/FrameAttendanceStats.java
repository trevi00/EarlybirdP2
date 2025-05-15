package ui.attendance;

import domain.attendance.controller.AttendanceController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class FrameAttendanceStats extends JFrame {

    private final AttendanceController controller;
    private YearMonth selectedMonth;
    private JPanel calendarPanel;
    private JLabel lblSummary;

    public FrameAttendanceStats(AttendanceController controller) {
        this.controller = controller;
        this.selectedMonth = YearMonth.now();

        setTitle("출석 통계");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        updateCalendar();

        setVisible(true);
    }

    private void initUI() {
        JPanel top = new JPanel();
        JButton btnPrev = new JButton("◀");
        JButton btnNext = new JButton("▶");
        JLabel lblMonth = new JLabel(selectedMonth.toString(), SwingConstants.CENTER);

        top.add(btnPrev);
        top.add(lblMonth);
        top.add(btnNext);
        add(top, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        add(calendarPanel, BorderLayout.CENTER);

        lblSummary = new JLabel("", SwingConstants.CENTER);
        add(lblSummary, BorderLayout.SOUTH);

        btnPrev.addActionListener(e -> {
            selectedMonth = selectedMonth.minusMonths(1);
            lblMonth.setText(selectedMonth.toString());
            updateCalendar();
        });

        btnNext.addActionListener(e -> {
            selectedMonth = selectedMonth.plusMonths(1);
            lblMonth.setText(selectedMonth.toString());
            updateCalendar();
        });
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        Set<LocalDate> days = controller.getAttendanceDaysIn(selectedMonth);
        CalendarPanel panel = new CalendarPanel(selectedMonth, days);
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(panel, BorderLayout.CENTER);

        lblSummary.setText(String.format("출석일수: %d일 / %d일", days.size(), selectedMonth.lengthOfMonth()));

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}
