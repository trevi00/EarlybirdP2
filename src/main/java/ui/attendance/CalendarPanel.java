package ui.attendance;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class CalendarPanel extends JPanel {

    public CalendarPanel(YearMonth yearMonth, Set<LocalDate> attendanceDays) {
        setLayout(new GridLayout(0, 7));

        String[] weekdays = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : weekdays) {
            add(new JLabel(day, SwingConstants.CENTER));
        }

        LocalDate firstDay = yearMonth.atDay(1);
        int skip = firstDay.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < skip; i++) {
            add(new JLabel(""));
        }

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            JLabel label = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            if (attendanceDays.contains(date)) {
                label.setOpaque(true);
                label.setBackground(Color.GREEN);
                label.setForeground(Color.WHITE);
            }
            add(label);
        }
    }
}
