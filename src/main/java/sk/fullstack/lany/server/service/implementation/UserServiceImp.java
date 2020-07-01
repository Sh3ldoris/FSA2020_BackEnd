package sk.fullstack.lany.server.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fullstack.lany.server.dao.UserRepository;
import sk.fullstack.lany.server.model.User;
import sk.fullstack.lany.server.service.UserService;

import java.util.List;

/**
 * Implementation of methods which manage User data.
 */
@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Search for all user in databse.
     * @return List of all user.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Save a new User to database.
     * @param user Body of user that will be added.
     * @return New User.
     */
    @Override
    public User saveUser(User user) {
            user = encodePassword(user);
            userRepository.save(user);
            return user;
    }

    /**
     * Searching for user by his username.
     * @param username Username of wanted user.
     * @return Founded user.
     * @throws ItemNotFoundException In a case there is no user with given username.
     */
    @Override
    public User getUser(String username) throws ItemNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ItemNotFoundException("Cannot find " + username + "!");
        return user;
    }

    /**
     * Encoding of password for user
     * @param user Body of user
     * @return User with encoded password.
     */
    @Override
    public User encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  user;
    }


    /**
     * Checks if there is a certain user registered in database by his username.
     * @param username username.
     * @return True/false value of existence of user
     */
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


}
