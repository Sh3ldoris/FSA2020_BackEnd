package sk.fullstack.lany.server.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.model.DTO.LikedBookDTO;
import sk.fullstack.lany.server.model.DTO.UserNoteModel;
import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;
import sk.fullstack.lany.server.service.LikedBookService;
import sk.fullstack.lany.server.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EndPont for controller that manages user services.
 * User has access here only with valid token
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;
    private LikedBookService likedBookService;

    public UserController(ModelMapper modelMapper, UserService userService, LikedBookService likedBookService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.likedBookService = likedBookService;
    }

    /**
     * Searches users liked book by likedBook id
     * @param id id of liked book
     * @param username username of user
     * @return Users liked book with given id
     * @throws ItemNotFoundException in the case that cannot find book with given id.
     */
    @GetMapping(value = "/book")
    public LikedBookDTO getLikedBook(@RequestParam long id, @RequestParam String username) {
        try {
            return modelMapper.map(likedBookService.getLikedBook(id, username), LikedBookDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * All users liked book.
     * @param username username of user
     * @return a new List of all his liked books.
     * @throws ItemNotFoundException if there is no user with given username
     */
    @GetMapping(value = "/books/all")
    public List<LikedBookDTO> getAllLikedBooks(@RequestParam String username) throws ItemNotFoundException {
        return likedBookService.getAllLikedBook(username).stream()
                .map(book -> modelMapper.map(book, LikedBookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new liked book to user
     * @param bookToLike book that is beeing liked
     * @param username  username
     * @return message of that book was added to liked books or not
     */
    @PostMapping(value = "/books/like")
    public ResponseEntity likeBook(@RequestBody BookDTO bookToLike, @RequestParam String username) {
        try {
            likedBookService.likeNewBook(bookToLike.getId(), username);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Cancel of like on the book
     * @param bookId Id of book that user wants to unlike
     * @param username username of user
     * @return
     */
    @DeleteMapping(value = "/books/unLike")
    public ResponseEntity unLikeBook(@RequestParam Long bookId, @RequestParam String username) {
        LikedBook unlike;
        try {
            //get LIKED book by book id
            unlike = likedBookService.getLikedBook(bookId, username);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        //Delete liked book by likedBook id
        likedBookService.unLikeBook(unlike.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Updates a note of liked book
     * @param newNote body of note
     * @param bookId id of book that user want to note
     * @param username username of user
     * @return updated liked book
     */
    @PutMapping("book/update-note")
    public LikedBookDTO updateNote(@RequestBody UserNoteModel newNote, @RequestParam long bookId, @RequestParam String username) {
        LikedBook update = null;
        try {
            update = likedBookService.updateNote(bookId, username, newNote.getNotes());
            return modelMapper.map(update, LikedBookDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

}
