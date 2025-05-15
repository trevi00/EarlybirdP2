package ui.coupon;

import domain.coupon.controller.CouponController;
import domain.coupon.model.Coupon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrameCouponGallery extends JFrame {

    private final CouponController couponController;

    public FrameCouponGallery(CouponController controller) {
        this.couponController = controller;

        setTitle("내 쿠폰 보관함");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        List<Coupon> coupons = couponController.getUserCoupons();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Coupon c : coupons) {
            listModel.addElement("🎁 " + c.getType() + " (획득일: " + c.getReceivedDate() + ")");
        }

        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        add(new JScrollPane(list), BorderLayout.CENTER);
        setVisible(true);
    }
}
