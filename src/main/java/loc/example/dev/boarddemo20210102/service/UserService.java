package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.User;
import loc.example.dev.boarddemo20210102.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.passEncoder = passEncoder;
    }

    public void save(User user) {
        String pass = user.getPassword();
        pass = passEncoder.encode(pass);
        user.setPassword(pass);
        userRepo.save(user);
    }
}
