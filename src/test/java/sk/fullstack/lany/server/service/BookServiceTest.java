package sk.fullstack.lany.server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.fullstack.lany.server.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(3, allBooks.size());
    }

    @Test
    void testGetBookById() {
        Book testedBook;
        List<Book> allBooks = bookService.getAllBooks();
        long idB = allBooks.get(1).getId();
        System.out.println(idB);

        try {
            testedBook = bookService.getBook(idB);
        }
        catch(Exception e) {
            testedBook = null;
        }
        assertNotNull(testedBook);
        assertEquals("Potter", testedBook.getTitle());
    }

    @Test
    void testFindAllBooksByTitle() {
        List<Book> testedBooks = null;

        testedBooks = bookService.getBooksByTitle("Potter");

        assertNotNull(testedBooks);
        assertEquals("Potter",testedBooks.get(0).getTitle());
        assertEquals("Potter",testedBooks.get(1).getTitle());

        testedBooks = bookService.getBooksByTitle("Nothing");

        assertNotNull(testedBooks);
        assertEquals(0, testedBooks.size());
    }

}
