package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * [DatabaseConfig]
 * - Oracle DB 연결을 위한 설정 클래스
 * - Connection 객체를 반환한다.
 */
public class DatabaseConfig {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "overflow";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ DB 연결 실패: " + e.getMessage());
            return null;
        }
    }
}
