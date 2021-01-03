package loc.example.dev.boarddemo20210102.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

//    private User user;

    public void setPostId(String pid) {
        long id = Long.parseLong(pid);
        if (post == null) {
            post = new Post();
        }
        post.setId(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", post=" + post.getId() +
                '}';
    }
}
