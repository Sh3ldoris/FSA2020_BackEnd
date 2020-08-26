package sk.fullstack.lany.server.model;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import org.hibernate.annotations.ColumnDefault;
import sun.misc.BASE64Encoder;

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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String encodedImg;

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

    public Book( String title, String author, String isbn, String plot, String imgPath) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.plot = plot;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            File input = new File(imgPath);
            BufferedImage img = ImageIO.read(input);
            encodedImg = encodeToString(img, "jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
