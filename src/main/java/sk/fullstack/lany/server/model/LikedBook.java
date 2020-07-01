package sk.fullstack.lany.server.model;

import javax.persistence.*;

/**
 * LikedBook representing book, which was liked by certain user.
 * It holds reference to user that liked this book and also reference on book itself.
 * On the top of that it has user note.
 */
@Entity
public class LikedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Book book;

    @OneToOne()
    private User user;

    @Column(length=500)
    private String notes = "";

    public LikedBook(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public LikedBook() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
