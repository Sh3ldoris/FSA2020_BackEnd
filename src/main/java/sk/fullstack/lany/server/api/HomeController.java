package sk.fullstack.lany.server.api;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.fullstack.lany.server.model.DTO.UserDTO;
import sk.fullstack.lany.server.model.User;
import sk.fullstack.lany.server.service.UserService;

/**
 * EndPoint for controller that manages registration of new users.
 * Available without any authorization.
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    private final ModelMapper modelMapper;
    public final UserService userService;

    public HomeController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    /**
     * Save a new user to database in the case he is valid to register.
     * @param newUser book of new user
     * @return message of that he was registered or not
     */
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity registerNewUser(@RequestBody UserDTO newUser) {
        User potentialUser = modelMapper.map(newUser, User.class);

        if (newUser.getUsername() != null) {
            if (userService.existsByUsername(newUser.getUsername())) {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }

            userService.saveUser(potentialUser);
            return  new ResponseEntity(HttpStatus.OK);
        }

       return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }



}
