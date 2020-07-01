package sk.fullstack.lany.server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sk.fullstack.lany.server.api.HomeController;
import sk.fullstack.lany.server.model.DTO.UserDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    void newRegistrationTest() {
        UserDTO newUser = new UserDTO();
        newUser.setUsername("newUser");
        newUser.setPassword("newUser123");
        newUser.setRole("USER");

        assertEquals(new ResponseEntity(HttpStatus.CREATED), homeController.registerNewUser(newUser));

        //Negative scenario
        UserDTO failUser = new UserDTO();
        newUser.setUsername("dan");
        newUser.setPassword("dan123");
        newUser.setRole("USER");

        assertNotEquals(new ResponseEntity(HttpStatus.CREATED), homeController.registerNewUser(newUser));
    }
}
