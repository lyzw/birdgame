package mobi.birdgame.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhouwei on 2016/8/31.
 */
@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping("init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usermgmt/usermgmt");
        return modelAndView;
    }

    public ModelAndView getUserList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usermgmt/usermgmt");
        return modelAndView;
    }
}
