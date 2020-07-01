package sk.fullstack.lany.server.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.model.DTO.LikedBookDTO;
import sk.fullstack.lany.server.api.UserController;
import sk.fullstack.lany.server.model.DTO.UserNoteModel;
import sk.fullstack.lany.server.service.BookService;
import sk.fullstack.lany.server.service.LikedBookService;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private LikedBookService likedBookService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testGetAllLikedBooks() {
        List<LikedBookDTO> testedBooks;
        try {
            testedBooks = userController.getAllLikedBooks("admin");
        } catch (Exception e) {
            testedBooks = null;
        }
        assertNotNull(testedBooks);
        assertEquals(2, testedBooks.size());
    }

    @Test
    void testGetLikedBook() {
        LikedBookDTO testedBook;
        try {
            testedBook = userController.getLikedBook(4, "dan");
        } catch (Exception e) {
            testedBook = null;
        }

        assertNotNull(testedBook);
        assertEquals("Game", testedBook.getBook().getTitle());
    }

    @Test
    void testLikeNewBook() {
        try {
            Book bookToLike = this.bookService.getBook(6);
            userController.likeBook(modelMapper.map(bookToLike, BookDTO.class), "dan");
        } catch(Exception e) {
            System.err.println(e);
        }

        List<LikedBookDTO> testedBooks;
        try {
            testedBooks = userController.getAllLikedBooks("dan");
        } catch (Exception e) {
            testedBooks = null;
        }
        assertNotNull(testedBooks);
        assertEquals(4, testedBooks.size());

    }

    @Test
    void testUnLikeBook() {
        String username = "manager";

        assertEquals(new ResponseEntity(HttpStatus.OK), userController.unLikeBook(4l, username));
        List<LikedBookDTO> testedBooks;
        try {
            testedBooks = userController.getAllLikedBooks(username);
        } catch (Exception e) {
            testedBooks = null;
        }
        assertNotNull(testedBooks);
        assertEquals(0, testedBooks.size());

    }

    @Test
    void updateNoteTest() {
        UserNoteModel note =  new UserNoteModel();
        note.setNote("Moja nova poznamka");

        LikedBookDTO response = userController.updateNote(note, 4, "manager");
        assertNotNull(response);
        assertEquals("Game",response.getBook().getTitle());

        assertNull(userController.updateNote(note, 122, "dan"));
    }

    @Test
    void testDeleteAllLikedBooks() {
        try {
            assertEquals(2, this.likedBookService.getAllLikedBook("admin").size());
            assertEquals(1, this.likedBookService.getAllLikedBook("manager").size());

            this.likedBookService.deleteAllLikesByBookId(4l);

            assertEquals(1, this.likedBookService.getAllLikedBook("admin").size());
            assertEquals(0, this.likedBookService.getAllLikedBook("manager").size());
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("CHYBAAAAAAAAAAAAAAA");
            return;
        }


    }
}
