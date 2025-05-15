package app.launcher;

import app.context.EarlyBirdContext;
import ui.user.FrameLogin;

/**
 * [AppLauncher]
 * - 프로그램 진입점 (main)
 * - Context 생성 및 로그인 화면 시작
 */
public class AppLauncher {
    public static void main(String[] args) {
        EarlyBirdContext context = new EarlyBirdContext();
        new FrameLogin(context.userController, context.birdMessageManager);
    }
}
