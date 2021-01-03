package loc.example.dev.boarddemo20210102.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter // do not use @Data annotation because it causes infinite recursive calls
@Setter
@Entity
public class Post extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

//    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> commentList;

    public void addComment(Comment cmnt) {
        if (commentList == null) {
            commentList = new HashSet<>();
        }
        cmnt.setPost(this);
        commentList.add(cmnt);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}