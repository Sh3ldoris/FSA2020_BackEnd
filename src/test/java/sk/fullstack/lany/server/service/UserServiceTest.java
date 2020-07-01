package sk.fullstack.lany.server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.fullstack.lany.server.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveNewUser() {
        List<User> usersOld = userService.getAllUsers();
        User newUser = new User ("sheldor", "adam123", "ADMIN");
        userService.saveUser(newUser);
        List<User> usersNew = userService.getAllUsers();
        assertNotNull(usersNew);
        assertEquals(usersOld.size() + 1, usersNew.size());
    }

    @Test
    public void testFindByUserName() {
        User newUser = new User ("NovyPou", "heslo123", "ADMIN");
        userService.saveUser(newUser);

        User foundUser;
        try {
            foundUser = userService.getUser(newUser.getUsername());
        } catch (Exception e) {
            foundUser = null;
        }
        assertNotNull(foundUser);
        assertEquals(newUser.getUsername(), foundUser.getUsername());
    }


}
