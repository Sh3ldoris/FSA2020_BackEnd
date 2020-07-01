package sk.fullstack.lany.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.model.User;

import java.util.List;

/**
 * Manage connection to database of liked books.
 */
@Repository
public interface LikedBookRepository extends JpaRepository<LikedBook, Long> {
    List<LikedBook> findByUserOrderByBookTitle(User user);
    LikedBook findByBookAndAndUser(Book book, User user);
    void deleteAllByBook_Id(long id);
}
