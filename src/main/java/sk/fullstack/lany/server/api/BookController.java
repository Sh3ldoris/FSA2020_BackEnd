package sk.fullstack.lany.server.api;

import org.modelmapper.ModelMapper;

import org.springframework.web.bind.annotation.*;
import sk.fullstack.lany.server.model.DTO.BookDTO;
import sk.fullstack.lany.server.service.BookService;
import sk.fullstack.lany.server.service.implementation.ItemNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * EndPoint for controller that manages books.
 * It is available without any authorization.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    /**
     * All books in the database
     * @return A new list of all books.
     */
    @GetMapping(value="/all")
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Searching for book by its id.
     * @param id id of searching book
     * @return Founded book
     * @throws ItemNotFoundException In the case that there is no book with given id.
     */
    @GetMapping(value="/{id}")
    public BookDTO getBook(@PathVariable Long id) throws ItemNotFoundException {
        return modelMapper.map(bookService.getBook(id), BookDTO.class);
    }

    /**
     * Searching book by it title.
     * @param title title of book
     * @return a new list of founded books
     */
    @GetMapping()
    public List<BookDTO> getBooksByTitle(@RequestParam(value = "title") String title) {
        //Used a query param for spring dont know which getMethod with param use
        return bookService.getBooksByTitle(title).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }




}
