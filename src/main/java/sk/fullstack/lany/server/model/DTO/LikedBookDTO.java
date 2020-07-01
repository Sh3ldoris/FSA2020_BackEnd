package sk.fullstack.lany.server.model.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.User;

/**
 * Its purpose is to map data from client side to LikedBook entity.
 * Also it is used when data are send to client side.
 * Class LikedBookDTO je trieda, ktorá slúži na mapovanie údajov z klient strany na LikesBook class.
 * Taktiež sa odosiela na klient stranu.
 */
public class LikedBookDTO {
    private Book book;
    private String notes;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
