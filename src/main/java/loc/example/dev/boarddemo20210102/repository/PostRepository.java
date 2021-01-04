package loc.example.dev.boarddemo20210102.repository;

import loc.example.dev.boarddemo20210102.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);
}
