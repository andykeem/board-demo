package loc.example.dev.boarddemo20210102.controller.advice;

import loc.example.dev.boarddemo20210102.exception.UsernameExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    @ExceptionHandler(value = UsernameExistException.class)
    public ModelAndView handleUserExistException(HttpServletRequest request,
                                                 UsernameExistException e) {
        logger.error("exception: {}", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}