// ui.attendance.FrameAttendanceStats.java
package ui.attendance;

import domain.attendance.controller.AttendanceController;
import domain.attendance.service.AttendanceStatsService;
import user.model.User;
import user.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class FrameAttendanceStats extends JFrame {

    private final AttendanceController attendanceController;
    private final AttendanceStatsService statsService;
    private final User user;

    private YearMonth selectedMonth;
    private JPanel calendarContainer;
    private JLabel lblSummary;

    public FrameAttendanceStats(AttendanceController attendanceController) {
        this.attendanceController = attendanceController;
        this.statsService = attendanceController.getStatsService();
        this.user = SessionManager.getCurrentUser();
        this.selectedMonth = YearMonth.now();

        setTitle("출석 통계");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        updateCalendar();
        setVisible(true);
    }

    private void initUI() {
        JPanel topPanel = new JPanel();
        JButton btnPrev = new JButton("◀");
        JButton btnNext = new JButton("▶");
        JLabel lblMonth = new JLabel(selectedMonth.toString(), SwingConstants.CENTER);

        topPanel.add(btnPrev);
        topPanel.add(lblMonth);
        topPanel.add(btnNext);
        add(topPanel, BorderLayout.NORTH);

        calendarContainer = new JPanel(new BorderLayout());
        add(calendarContainer, BorderLayout.CENTER);

        lblSummary = new JLabel("", SwingConstants.CENTER);
        lblSummary.setFont(new Font("Serif", Font.BOLD, 14));
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
        calendarContainer.removeAll();

        Set<LocalDate> days = statsService.getAttendanceDaysInMonth(user.getUsername(), selectedMonth);
        CalendarPanel panel = new CalendarPanel(selectedMonth, days);
        calendarContainer.add(panel, BorderLayout.CENTER);

        int attended = days.size();
        int total = selectedMonth.lengthOfMonth();
        lblSummary.setText(String.format("출석일수: %d / %d (출석률 %.1f%%)", attended, total, (attended * 100.0 / total)));

        calendarContainer.revalidate();
        calendarContainer.repaint();
    }
}
