package loc.example.dev.boarddemo20210102.service;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        Optional<Post> optPost = postRepo.findById(id);
        if (optPost.isPresent()) { // reverse comment(s) order
            Set<Comment> cmnts = optPost.get().getCommentList();
            Set<Comment> sorted = cmnts.stream().sorted(new Comparator<Comment>() {
                @Override
                public int compare(Comment c, Comment c2) {
                    Long id = c.getId();
                    Long id2 = c2.getId();
                    return id2.compareTo(id); // reverse the order..
                }
            }).collect(Collectors.toCollection(LinkedHashSet::new));
            optPost.get().setCommentList(sorted);
            return optPost.get();
        }
        return new Post();
    }

    public void deleteById(long id) {
        postRepo.deleteById(id);
    }
}
