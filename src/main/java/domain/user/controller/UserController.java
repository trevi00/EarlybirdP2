// domain.user.controller.UserController.java
package domain.user.controller;

import domain.user.model.User;
import domain.user.service.UserService;

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public boolean register(String username, String password, String displayName) {
        return service.register(username, password, displayName);
    }

    public User login(String username, String password) {
        return service.login(username, password);
    }

    public boolean exists(String username) {
        return service.exists(username);
    }
}
