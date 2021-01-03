package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PostService {

    private final PostRepository postRepo;

    @Autowired
    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public Post save(Post post) {
        return postRepo.save(post);
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public List<Post> findAllByOrderByIdDesc() {
        return postRepo.findAllByOrderByIdDesc();
    }

    public Post findById(long id) {
        Optional<Post> optPost = postRepo.findByIdOrderByCommentIdDesc(id);
        return optPost.orElseGet(Post::new);
    }
}
