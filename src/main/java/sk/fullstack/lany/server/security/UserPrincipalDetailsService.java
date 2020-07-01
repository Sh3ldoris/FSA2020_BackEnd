package sk.fullstack.lany.server.security;

import org.springframework.stereotype.Service;
import sk.fullstack.lany.server.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sk.fullstack.lany.server.dao.UserRepository;

/**
 * Service that searches for user in datanase and create a UserDetails from him.
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.findByUsername(username);
        UserPrincipal principal = new UserPrincipal(user);
        return principal;
    }
}
