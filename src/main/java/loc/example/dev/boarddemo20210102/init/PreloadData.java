package loc.example.dev.boarddemo20210102.init;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.model.DummyPost;
import loc.example.dev.boarddemo20210102.repository.PostRepository;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Component
public class PreloadData implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PostRepository postRepo;

    @Autowired
    public PreloadData(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("contextRefreshedEvent: {}", contextRefreshedEvent);

//        importPostData();
    }

    private void importPostData() {
        List<DummyPost> posts = getDummyPosts();
        logger.info("posts: {}", posts);

        importPosts(posts);
    }

    private void importPosts(List<DummyPost> posts) {
        posts.forEach(new Consumer<DummyPost>() {
            @Override
            public void accept(DummyPost dummyPost) {
                int numClicks = ThreadLocalRandom.current().nextInt(50);
                Post post = new Post(
                        dummyPost.getId(),
                        dummyPost.getTitle(),
                        dummyPost.getBody(),
                        numClicks
                );
                postRepo.save(post);
            }
        });
    }

    private List<DummyPost> getDummyPosts() {
        List<DummyPost> posts = new ArrayList<>();
        String filepath = "/data/posts.json";
        String jsonStr = readResourceByFilepath(filepath);
//        logger.info("json file content: {}", jsonStr);

        ObjectMapper objMapper = new ObjectMapper();
        try {
            DummyPost[] arr = objMapper.readValue(jsonStr, DummyPost[].class);
            posts = Arrays.asList(arr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private String readResourceByFilepath(String filepath) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        try {
            in = new BufferedInputStream(getClass().getResourceAsStream(filepath));
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
            }
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
