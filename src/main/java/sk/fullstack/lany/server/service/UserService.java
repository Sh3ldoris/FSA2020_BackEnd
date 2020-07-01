package sk.fullstack.lany.server.service;

import sk.fullstack.lany.server.model.User;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;

import java.util.List;

/**
 * Interface of methods that can be called on the top of User data.
 */
public interface UserService {

    List<User> getAllUsers();
    User saveUser(User user);
    User getUser(String username) throws ItemNotFoundException;
    User encodePassword(User potentialUser);
    boolean existsByUsername(String username);
}
