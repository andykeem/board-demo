package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.User;
import loc.example.dev.boarddemo20210102.exception.UserNotFoundException;
import loc.example.dev.boarddemo20210102.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public DaoUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepo.findByUsername(username);
        if (user == null) {
            String msg = String.format("Could not find user with username: %s", username);
//            throw new UserNotFoundException(msg);
            throw new UsernameNotFoundException(msg);
        }
        return user;
    }
}
