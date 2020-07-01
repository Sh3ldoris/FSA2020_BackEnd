package sk.fullstack.lany.server.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sk.fullstack.lany.server.api.AdminController;
import sk.fullstack.lany.server.api.BookController;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.service.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;
    @Autowired
    private BookService bookService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testUpdateBook() {
        BookDTO update;
        try {
            update = modelMapper.map( this.bookService.getBook(4), BookDTO.class);
            update.setAuthor("New Author");
        } catch (Exception e) {
            update = null;
        }

        if (update != null) {
            adminController.updateBook(update);
        }

        Book book;

        try {
            book = this.bookService.getBook(4);
        } catch (Exception e) {
            book = null;
        }

        assertNotNull(book);
        assertEquals("New Author", book.getAuthor());
    }

    @Test
    public void testDeleteBook() {
        assertEquals(4, bookService.getAllBooks().size());
        assertEquals(new ResponseEntity(HttpStatus.OK), adminController.deleteBook(7l));
        assertNotNull(bookService.getAllBooks());
        assertEquals(3, bookService.getAllBooks().size());
    }
}
