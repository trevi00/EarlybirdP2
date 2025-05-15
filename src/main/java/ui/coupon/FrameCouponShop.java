package ui.coupon;

import domain.coupon.controller.CouponController;
import ui.component.BirdMessageManager;

import javax.swing.*;
import java.awt.*;

public class FrameCouponShop extends JFrame {

    private final CouponController couponController;
    private final BirdMessageManager messageManager;

    public FrameCouponShop(CouponController controller, BirdMessageManager messageManager) {
        this.couponController = controller;
        this.messageManager = messageManager;

        setTitle("포인트 상점");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1, 10, 10));

        add(createCouponButton("비타500 쿠폰", "vitamin", 200));
        add(createCouponButton("콜라 쿠폰", "cola", 300));
        add(createCouponButton("커피 쿠폰", "coffee", 500));

        setVisible(true);
    }

    private JButton createCouponButton(String label, String type, int cost) {
        JButton button = new JButton(label + " - " + cost + "P");
        button.addActionListener(e -> {
            boolean success = couponController.redeem(type, cost);
            if (success) {
                JOptionPane.showMessageDialog(this, "쿠폰 교환 성공!");
                messageManager.say("새 쿠폰이 추가되었어요!");
                messageManager.speakRandom();
            } else {
                JOptionPane.showMessageDialog(this, "포인트가 부족합니다.");
            }
        });
        return button;
    }
}
