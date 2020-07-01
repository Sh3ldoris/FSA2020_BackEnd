package sk.fullstack.lany.server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.fullstack.lany.server.model.DTO.LikedBookDTO;
import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LikedBookServiceTest {

    @Autowired
    private LikedBookService likedBookService;

    @Test
    void likeNewBookTest() {
        try {
            likedBookService.likeNewBook(4, "dan");
        } catch (Exception e) {
            assertEquals("LikedBook with id: 4 for user: dan already exists!", e.getMessage());
        }
    }
}
