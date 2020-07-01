package sk.fullstack.lany.server.service;

import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;

import java.util.List;

/**
 * Interface of methods that can be called on the top of LikedBooks data.
 */
public interface LikedBookService {

    void likeNewBook(long bookId, String username) throws Exception;
    void unLikeBook(long likedBookId);
    List<LikedBook> getAllLikedBook(String userName) throws ItemNotFoundException;
    LikedBook getLikedBook(long bookId, String username) throws ItemNotFoundException;
    LikedBook updateNote(long bookId, String username, String note) throws ItemNotFoundException;
    void deleteAllLikesByBookId(long id);
}
