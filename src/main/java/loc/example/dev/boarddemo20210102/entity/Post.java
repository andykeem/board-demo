package loc.example.dev.boarddemo20210102.entity;

import com.sun.source.tree.Tree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
    private int numClicks;

//    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> commentList;

    public Post(long id, String title, String description, int numClicks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numClicks = numClicks;
    }

    public void addComment(Comment cmnt) {
        if (commentList == null) {
            commentList = new HashSet<>();
        }
        cmnt.setPost(this);
        commentList.add(cmnt);
    }

    public int numComments() {
        return commentList.size();
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