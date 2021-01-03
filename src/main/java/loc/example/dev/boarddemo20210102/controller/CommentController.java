package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.Comment;
import loc.example.dev.boarddemo20210102.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final CommentService cmntService;

    @Autowired
    public CommentController(CommentService cmntService) {
        this.cmntService = cmntService;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("comment") Comment cmnt,
                       @RequestParam("post_id") String postId,
                       BindingResult result, RedirectAttributes redirect) {
        logger.info("comment: {}", cmnt);
        if (result.hasErrors()) {
            redirect.addFlashAttribute("isCommentAdded", false);
            return "redirect:/post/view/" + postId;
        }
        // save comment..
        Comment savedCmnt = cmntService.save(cmnt, postId);
        redirect.addFlashAttribute("isCommentAdded", true);
        return "redirect:/post/view/" + postId;
    }
}
