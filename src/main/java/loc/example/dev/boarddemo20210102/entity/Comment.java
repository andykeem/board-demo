package loc.example.dev.boarddemo20210102.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Entity
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 6, max = 255, message = "must be greater than 6 characters")
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setPostId(String pid) {
        long id = Long.parseLong(pid);
        if (post == null) {
            post = new Post();
        }
        post.setId(id);
    }

    @Override
    public String toString() {
        long postId = 0;
        if (post != null) {
            postId = post.getId();
        }
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", post=" + postId +
                '}';
    }
}
