package loc.example.dev.boarddemo20210102.controller;

import loc.example.dev.boarddemo20210102.entity.User;
import loc.example.dev.boarddemo20210102.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        model.addAttribute(new User());
        return "user/register";
    }

    @PostMapping(path = "/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        }
        userService.save(user);
        return "redirect:/";
    }

    /*@ExceptionHandler(value = ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationError(HttpServletRequest request,
                                                       ConstraintViolationException e) {
        logger.error("Exception: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.setViewName("error");
        return mav;
    }*/
}
