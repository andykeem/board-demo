package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository cmntRepo;

    @Autowired
    public CommentService(CommentRepository cmntRepo) {
        this.cmntRepo = cmntRepo;
    }

    public Comment save(Comment cmnt, String postId) {
        cmnt.setPostId(postId);
        return cmntRepo.save(cmnt);
    }
}
