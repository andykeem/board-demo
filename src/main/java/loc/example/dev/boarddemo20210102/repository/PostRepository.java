package loc.example.dev.boarddemo20210102.repository;

import loc.example.dev.boarddemo20210102.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();

    Optional<Post> findByIdOrderByCommentIdDesc(Long id);
}
