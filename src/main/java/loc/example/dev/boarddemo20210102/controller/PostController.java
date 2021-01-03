package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        logger.info("PostController.add() method called..");
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/add";
    }

    @PostMapping(path = "/save")
    public String save(@ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/add";
        }
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping(path = "/view/{id}")
    public String view(@PathVariable int id, Model model) {
        logger.info("view post id: {}", id);
        Post post = postService.findById(id);
        model.addAttribute("post", post);

        Comment cmnt = new Comment();
        model.addAttribute("comment", cmnt);
        return "/post/view";
    }
}
