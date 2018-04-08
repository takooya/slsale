package org.slsale.controller.user;

import org.apache.log4j.Logger;
import org.slsale.pojo.user.User;
import org.slsale.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author takooya
 * @Description
 * @Date:created in 23:22 2018/4/8
 */
@Controller
public class UserController {
    private Logger log= Logger.getLogger(UserController.class.toString());
    @Autowired
    private UserService userService;

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/doLogin.html")
    public ModelAndView doLogin(User user){
        log.debug("UserController.doLogin==>user:"+user);
        try {
            user = userService.getLoginUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("loginSuccess");
    }
    @RequestMapping("/exit.html")
    public String exit(){
        return "exit";
    }
    @RequestMapping("/register.html")
    public String register(){
        return "register";
    }
    @RequestMapping("/doRegister.html")
    public ModelAndView doRegister(User user){
        int f=0;
        try {
            f=userService.addUser(user);
            if (f>0){
                user=userService.getLoginUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("regSuccess");
    }
}
