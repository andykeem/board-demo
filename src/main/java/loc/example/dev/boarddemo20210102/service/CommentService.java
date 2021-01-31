package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.entity.User;
import loc.example.dev.boarddemo20210102.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CommentService {

    private final CommentRepository cmntRepo;
    private final DaoUserDetailsService userService;

    @Autowired
    public CommentService(CommentRepository cmntRepo, DaoUserDetailsService userService) {
        this.cmntRepo = cmntRepo;
        this.userService = userService;
    }

    public Comment save(Comment cmnt, String postId) {
        cmnt.setPostId(postId);
        return cmntRepo.save(cmnt);
    }

    public Comment save(Comment cmnt, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        cmnt.setUser(user);
        return cmntRepo.save(cmnt);
    }
}
