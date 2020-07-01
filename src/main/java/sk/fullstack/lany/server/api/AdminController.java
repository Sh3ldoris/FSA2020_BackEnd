package sk.fullstack.lany.server.api;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.service.BookService;
import sk.fullstack.lany.server.service.LikedBookService;

/**
 * EndPoint for extended controller of Books.
 * User has access here only with role of ADMIN
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final BookService bookService;
    private final ModelMapper modelMapper;
    private final LikedBookService likedBookService;

    public AdminController(BookService bookService, ModelMapper modelMapper, LikedBookService likedBookService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.likedBookService = likedBookService;
    }

    /**
     * Updates Book
     * @param bookToUpdate Body of updated book.
     * @return Updated book.
     */
    @PutMapping("/update")
    public BookDTO updateBook(@RequestBody BookDTO bookToUpdate) {
        Book update = null;
        update = bookService.updateBook(modelMapper.map(bookToUpdate, Book.class));
        return modelMapper.map(update, BookDTO.class);
    }

    /**
     * Delete book by its id.
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam Long id) {
        this.likedBookService.deleteAllLikesByBookId(id);
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Adds a new Book.
     * @param newBook Body of new Book.
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addNewBook(@RequestBody BookDTO newBook) {
        if (newBook.getIsbn() != null && newBook.getAuthor() != null && newBook.getTitle() != null) {

            if (bookService.getBooksByIsbn(newBook.getIsbn()) != null)
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ISBN already exists!");

            bookService.addBook(modelMapper.map(newBook, Book.class));
            return ResponseEntity.status(HttpStatus.OK).body("Everything okay!");
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
