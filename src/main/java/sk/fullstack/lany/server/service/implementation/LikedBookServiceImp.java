package sk.fullstack.lany.server.service.implementation;

import org.springframework.stereotype.Service;
import sk.fullstack.lany.server.dao.LikedBookRepository;
import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.service.BookService;
import sk.fullstack.lany.server.service.LikedBookService;
import sk.fullstack.lany.server.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of methods which manage LikedBooks data.
 */
@Service
public class LikedBookServiceImp implements LikedBookService {

    private LikedBookRepository likedBookRepository;
    private BookService bookService;
    private UserService userService;

    public LikedBookServiceImp(LikedBookRepository likedBookRepository, BookService bookService, UserService userService) {
        this.likedBookRepository = likedBookRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    /**
     * Add a liked status to a certain book.
     * @param bookId Id of book which is wanted to be liked
     * @param username Username of user who likes a book.
     * @throws Exception If there a problem and book cannot be like.
     */
    @Override
    public void likeNewBook(long bookId, String username) throws Exception {
        LikedBook likedBook = null;
        LikedBook newLike = null;
        try {
            likedBook = likedBookRepository.findByBookAndAndUser(bookService.getBook(bookId), userService.getUser(username));
            newLike = new LikedBook(bookService.getBook(bookId), userService.getUser(username));
        } catch (ItemNotFoundException e) {
            throw new Exception(e);
        }

        if (likedBook != null) {
            throw new Exception("LikedBook with id: " + bookId + " for user: " + username + " already exists!");
        } else if (newLike != null)
            likedBookRepository.save(newLike);
    }

    /**
     * Delete liked status of a certain book
     * @param likedBookId Id of book which is wanted to be unlike.
     */
    @Override
    public void unLikeBook(long likedBookId) {
        likedBookRepository.deleteById(likedBookId);
    }

    /**
     * Searching for a list of all books that user has liked
     * @param userName Username of user
     * @return New List of all liked books that belong to user.
     * @throws ItemNotFoundException If there is no user with givent username.
     */
    @Override
    public List<LikedBook> getAllLikedBook(String userName) throws ItemNotFoundException{
        return likedBookRepository.findByUserOrderByBookTitle(userService.getUser(userName));
    }

    /**
     * Searching for one certain liked book that belongs to user
     * @param BookId Id of Book which is liked.
     * @param username Username of user.
     * @return Found book.
     * @throws ItemNotFoundException In case that user or book cannot be find.
     */
    @Override
    public LikedBook getLikedBook(long BookId, String username) throws ItemNotFoundException {
        LikedBook like = likedBookRepository.findByBookAndAndUser(bookService.getBook(BookId), userService.getUser(username));
        if (like == null)
            throw new ItemNotFoundException("LikedBook with id: " + BookId + " fro user: " + username + " does not exists!");
        return like;
    }

    /**
     * Update note to certain book liked book/
     * @param bookId Id of Book which is liked.
     * @param username Username of user.
     * @param note Note that will be added to present note.
     * @return Updated LikedBook with new note.
     * @throws ItemNotFoundException
     */
    @Override
    public LikedBook updateNote(long bookId, String username, String note) throws ItemNotFoundException {
        LikedBook update = this.getLikedBook(bookId, username);
        update.setNotes(note);
        likedBookRepository.save(update);
        return update;
    }

    /**
     * Delete all books with given Id.
     * @param id Id of likedBook which is wanted to be deleted.
     */
    @Override
    @Transactional
    public void deleteAllLikesByBookId(long id) {
        this.likedBookRepository.deleteAllByBook_Id(id);
    }
}
