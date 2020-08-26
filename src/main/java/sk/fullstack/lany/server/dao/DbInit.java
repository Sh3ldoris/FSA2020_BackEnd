package sk.fullstack.lany.server.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fullstack.lany.server.model.Book;
import sk.fullstack.lany.server.model.LikedBook;
import sk.fullstack.lany.server.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Inicializovanie databázy údajov po spustení
 */
@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private BookRepository bookRepository;
    private LikedBookRepository likedBookRepository;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, BookRepository bookRepository, LikedBookRepository likedBookRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
        this.likedBookRepository = likedBookRepository;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User dan = new User("dan",passwordEncoder.encode("dan123"),"USER");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN");

        List<User> users = Arrays.asList(dan,admin);

        // Save to db
        this.userRepository.saveAll(users);


        FileReader input = null;
        try {
            input = new FileReader("BookTestImage.txt");
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        if (input != null) {
            try {
                BufferedReader bufRead = new BufferedReader(input);
                String myLine = null;

                while ( (myLine = bufRead.readLine()) != null)
                {
                    String[] data = myLine.split(";");
                    if (data.length == 5) {
                        bookRepository.save(new Book(data[0], data[1], data[2], data[3], data[4]));
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }


    }
}
