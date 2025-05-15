package domain.coupon.repository;

import domain.coupon.model.Coupon;
import domain.coupon.model.CouponType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserCouponRepository implements UserCouponRepository {

    private final Connection conn;

    public JdbcUserCouponRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Coupon> findAllByUserId(String userId) {
        List<Coupon> result = new ArrayList<>();
        String sql = "SELECT * FROM USER_COUPONS WHERE user_id = ? ORDER BY obtained_date DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new Coupon(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        CouponType.valueOf(rs.getString("type")),
                        rs.getDate("obtained_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
