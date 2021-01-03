package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class HomeConroller {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PostService postService;

    @Autowired
    public HomeConroller(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(Model model) {
        logger.info("HomeController.index() method called..");
        List<Post> postList = postService.findAllByOrderByIdDesc();
        model.addAttribute("postList", postList);
        return "index";
    }
}
