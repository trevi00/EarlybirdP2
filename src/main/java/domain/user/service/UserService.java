// domain.user.service.UserService.java
package domain.user.service;

import domain.user.model.User;

public interface UserService {
    User login(String username, String password);
    boolean register(String username, String password, String displayName);
    boolean exists(String username);
}
