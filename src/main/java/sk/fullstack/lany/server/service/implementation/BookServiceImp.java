package sk.fullstack.lany.server.service.implementation;

import org.springframework.stereotype.Service;
import sk.fullstack.lany.server.dao.BookRepository;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.service.BookService;

import java.util.List;

/**
 * Implementation of methods which manage Books data.
 */
@Service
public class BookServiceImp implements BookService {

    private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * @return list of all books in database
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    /**
     * Searching books by its title
     * @param title title of searching book
     * @return books whit that certain tilte
     */
    @Override
    public List<Book> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findAllByTitle(title);
        return books;
    }

    @Override
    public Book getBooksByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    /**
     * Searching book by its id.
     * @param id Id of book which is searched
     * @return Book with that certain id
     * @throws ItemNotFoundException If book with searched id does not exists
     */
    @Override
    public Book getBook(long id) throws ItemNotFoundException {
        Book foundBook = bookRepository.findById(id).orElse(null);
        if (foundBook == null)
            throw new ItemNotFoundException("Book with id="+id+" not found.");
        return foundBook;
    }

    /**
     * Update certain book
     * @param book updated body of book
     * @return Updated book.
     */
    @Override
    public Book updateBook(Book book) {
        this.bookRepository.save(book);
        return book;
    }

    /**
     * Delete book by its id.
     * @param id Id of book which is wanted to be deleted
     */
    @Override
    public void deleteBook(long id) {
        this.bookRepository.deleteById(id);
    }

    /**
     * Adding a new book to database
     * @param newBook A book that will be added.
     * @return Added book.
     */
    @Override
    public Book addBook(Book newBook) {
        bookRepository.save(newBook);
        return newBook;
    }
}
