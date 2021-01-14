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

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final CommentService cmntService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService cmntService, PostService postService) {
        this.cmntService = cmntService;
        this.postService = postService;
    }

    /*@PostMapping("/save")
    public String save(@Valid @ModelAttribute("comment") Comment cmnt,
                       BindingResult result,
                       @RequestParam("post_id") String postId,
                       RedirectAttributes redirect, Model model) {
        logger.info("comment: {}", cmnt);
        if (result.hasErrors()) {
            redirect.addFlashAttribute("isCommentAdded", false);
            Post post = postService.findById(postId);
            model.addAttribute(post);
            return "post/view"; // /" + postId;
        }
        // save comment..
        Comment savedCmnt = cmntService.save(cmnt, postId);
        redirect.addFlashAttribute("isCommentAdded", true);
        return "redirect:/post/view/" + postId;
    }*/
}
