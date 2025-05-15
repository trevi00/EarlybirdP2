// app/AppLauncher.java
package app;

import app.context.EarlyBirdContext;
import ui.user.FrameLogin;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        EarlyBirdContext context = new EarlyBirdContext();
        SwingUtilities.invokeLater(() -> new FrameLogin(context.userService));
    }
}
