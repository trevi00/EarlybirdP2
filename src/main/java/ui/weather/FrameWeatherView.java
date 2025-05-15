// ui.weather.FrameWeatherView.java
package ui.weather;

import domain.weather.controller.WeatherController;
import domain.weather.model.Weather;
import ui.message.BirdMessageManager;

import javax.swing.*;
import java.awt.*;

/**
 * 날씨 확인 화면 (Controller 기반)
 */
public class FrameWeatherView extends JFrame {

    private final WeatherController controller;
    private final BirdMessageManager messageManager;

    private JTextField cityField;
    private JButton btnSearch;
    private JLabel lblResult;

    public FrameWeatherView(WeatherController controller,
                            BirdMessageManager messageManager) {
        this.controller = controller;
        this.messageManager = messageManager;

        setTitle("날씨 확인");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        initUI();
        setVisible(true);
    }

    private void initUI() {
        // 상단: 도시 입력 + 버튼
        JPanel topPanel = new JPanel(new FlowLayout());
        cityField = new JTextField(15);
        btnSearch = new JButton("날씨 조회");
        topPanel.add(new JLabel("도시명:"));
        topPanel.add(cityField);
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        // 중앙: 결과 라벨
        lblResult = new JLabel("날씨 정보가 여기 표시됩니다.", SwingConstants.CENTER);
        lblResult.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        add(lblResult, BorderLayout.CENTER);

        // 버튼 액션
        btnSearch.addActionListener(e -> handleSearch());
    }

    private void handleSearch() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "도시명을 입력해주세요.");
            return;
        }

        Weather weather = controller.getTodayWeather(city);
        if (weather == null) {
            lblResult.setText("날씨 정보를 가져올 수 없습니다.");
        } else {
            lblResult.setText("<html>"
                    + "도시: " + weather.getCityName() + "<br>"
                    + "온도: " + weather.getTemperature() + "°C<br>"
                    + "상태: " + weather.getDescription()
                    + "</html>");

            // 새 메시지 출력
            messageManager.say("오늘 날씨는 " + weather.getDescription() + "입니다.");
            messageManager.speakRandom(null);  // userId 없이 랜덤 메시지
        }
    }
}
