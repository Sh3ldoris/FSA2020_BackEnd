package sk.fullstack.lany.server.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.fullstack.lany.server.api.BookController;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.service.BookService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;


    @Test
    void testGetAllBooks() {
        List<BookDTO> testedBooks = bookController.getAllBooks();
        assertNotNull(testedBooks);
        assertEquals(4, testedBooks.size());
    }

    @Test
    void testGetOneBook() {
        BookDTO testedBook;
        long id = 4;
        try {
            testedBook = bookController.getBook(id);
        } catch (Exception e) {
            testedBook = null;
        }
        assertNotNull(testedBook);
        assertEquals("Game", testedBook.getTitle());
        assertEquals("Adko", testedBook.getAuthor());
    }

    @Test
    void testGetBooksByName() {
        List<BookDTO> testedBooks = bookController.getBooksByTitle("Potter");
        assertNotNull(testedBooks);
        assertEquals(2, testedBooks.size());
        assertEquals("Potter", testedBooks.get(0).getTitle());

        testedBooks = bookController.getBooksByTitle("Nothing");
        assertNotNull(testedBooks);
        assertEquals(0, testedBooks.size());
    }


}
