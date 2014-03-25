package cn.easysale.web.admin.action;

import cn.easysale.core.entity.User;
import cn.easysale.core.service.UserService;
import cn.easysale.core.support.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liaozhisong on 3/22/14.
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        Subject user = SecurityUtils.getSubject();
        Object obj = user.getPrincipal();
        if (obj != null) return "redirect:welcome";
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User currUser) {
        return "redirect:login?username=" + currUser.getUsername();
    }
//
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public String logout() {
//        Subject user = SecurityUtils.getSubject();
//        user.logout();
//        return "redirect:login";
//    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome() {
        return "user/welcome";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(User user) {
        userService.addUser(user);
        return "redirect:login";
    }
}
