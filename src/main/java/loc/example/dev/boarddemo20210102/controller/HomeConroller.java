package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeConroller {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PostService postService;

    @Autowired
    public HomeConroller(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("term") Optional<String> term) {
        logger.info("HomeController.index() method called..");
        logger.info("page: {}", page);
        logger.info("search term: {}", term);

        int pg = page.orElse(1) - 1;
        Page<Post> post = postService.findAll(pg, term);
        model.addAttribute("post", post);

        if (post.getTotalPages() > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, post.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        logger.info("post.number: {}, post.size: {}, post.totalPages: {}",
                post.getNumber(), post.getSize(), post.getTotalPages());
        int num = post.getNumber();
        return "index";
    }
}
