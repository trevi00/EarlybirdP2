package domain.coupon.repository;

import domain.coupon.model.Coupon;
import domain.coupon.model.CouponType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class JdbcCouponRepository implements CouponRepository {

    private final Connection conn;

    public JdbcCouponRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Coupon coupon) {
        String sql = "INSERT INTO USER_COUPONS (id, user_id, type, obtained_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, coupon.getId());
            pstmt.setString(2, coupon.getUserId());
            pstmt.setString(3, coupon.getType().name());
            pstmt.setDate(4, Date.valueOf(coupon.getObtainedDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
