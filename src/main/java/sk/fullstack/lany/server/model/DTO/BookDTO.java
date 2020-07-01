package sk.fullstack.lany.server.model.DTO;

import javax.persistence.Column;

/**
 * Its purpose is to map data from client side to Book entity.
 * Also it is used when data are send to client side.
 */
public class BookDTO {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private String plot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
