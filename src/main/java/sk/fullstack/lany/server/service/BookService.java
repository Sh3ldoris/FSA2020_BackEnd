package sk.fullstack.lany.server.service;

import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;

import java.util.List;

/**
 * Interface of methods that can be called on the top of Books data.
 */
public interface BookService {

    List<Book> getAllBooks();
    List<Book> getBooksByTitle(String name);
    Book getBooksByIsbn(String isbn);
    Book getBook(long id) throws ItemNotFoundException;
    Book updateBook(Book book);
    void deleteBook(long id);
    Book addBook(Book newBook);

}
