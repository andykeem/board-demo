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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "post/edit";
    }

    @PostMapping(path = "/save")
    public String save(@ModelAttribute("post") Post post, BindingResult result,
                       RedirectAttributes redirect) {
        logger.info("post: {}", post);
        logger.info("result: {}", result);
        if (result.hasErrors()) {
            return "redirect:/edit";
        }
        postService.save(post);
        redirect.addFlashAttribute("message", "Your post has been updated");
        return "redirect:/";
    }

    @GetMapping(path = "/view/{id}")
    public String view(@PathVariable int id, Model model) {
        logger.info("view post id: {}", id);
        Post post = postService.findById(id);
        model.addAttribute("post", post);

        Comment cmnt = new Comment();
        model.addAttribute("comment", cmnt);

        postService.incrementNumClicks(post);
        return "/post/view";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/edit";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        postService.deleteById(id);
        String msg = String.format("Post id: %d has been deleted", id);
        redirect.addFlashAttribute("message", msg);
        return "redirect:/";
    }
}
