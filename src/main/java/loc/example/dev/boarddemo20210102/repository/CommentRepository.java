package loc.example.dev.boarddemo20210102.repository;

import loc.example.dev.boarddemo20210102.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
