package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.entity.Post;
import loc.example.dev.boarddemo20210102.service.CommentService;
import loc.example.dev.boarddemo20210102.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PostService postService;
    private final CommentService cmntService;

    @Autowired
    public PostController(PostService postService, CommentService cmntService) {
        this.postService = postService;
        this.cmntService = cmntService;
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
                       RedirectAttributes redirect, Principal principal) {
        logger.info("post: {}", post);
        logger.info("result: {}", result);
        if (result.hasErrors()) {
            return "redirect:/edit";
        }
        postService.save(post, principal);
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
        return "post/view";
    }

    // saves post's comment
    @PostMapping("view/{id}")
    public String saveComment(@PathVariable int id, @Valid @ModelAttribute("comment") Comment comment,
                              BindingResult result, RedirectAttributes redirect, Model model, Principal principal) {
        logger.info("comment: {}", comment);
        logger.info("principal: {}", principal);
        if (result.hasErrors()) {
            Post post = postService.findById(id);
            model.addAttribute("post", post);
            return "/post/view";
        }
        Comment cmnt = cmntService.save(comment, principal);
        logger.info("comment saved: {}", cmnt);

        return "redirect:/post/view/" + id;
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
