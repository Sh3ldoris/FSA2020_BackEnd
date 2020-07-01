package sk.fullstack.lany.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.fullstack.lany.server.model.Book;

import java.util.List;

/**
 * Manage connection to database of books.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByOrderByTitleAsc();

}
