package sk.fullstack.lany.server.model;

import javax.persistence.*;

/**
 * Class representing Book entity
 * it contains variables such as
 * id
 * title
 * author
 * plot
 * isbn
 *
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String author;

    @Column(length=1000)
    private String plot;

    private String isbn;

    public Book() {
    }

    public Book( String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book( String title, String author, String isbn, String plot) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.plot = plot;
    }

    public long getId() {
        return id;
    }

    public void setId(long bookId) {
        this.id = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
