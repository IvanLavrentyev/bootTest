package com.example.demo.controler;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleService;
import com.example.demo.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(ModelMap modelMap){
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addUser(@RequestParam String newUserName,
                          @RequestParam String newUserLogin,
                          @RequestParam String newUserPassword,
                          @RequestParam String roleParam,
                          ModelMap modelMap){

        Set<Role> roles = roleService.getAllRoles();

        if (!roleParam.equals("ADMIN"))
        roles.removeIf(role -> role.getRole_description().equals("ADMIN"));

        User addedUser = new User(newUserName,newUserLogin,newUserPassword,roles);

        userService.saveUser(addedUser);

        List<User> users = userService.getAllUsers();

        modelMap.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam long id){
        User deletedUser = userService.getUserById(id);
        userService.deleteUSer(deletedUser);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserPage(@RequestParam long id, ModelMap modelMap){
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String updateUser(@RequestParam String newUserName,
                             @RequestParam String newUserLogin,
                             @RequestParam String newUserPassword,
                             @RequestParam long id,
                             ModelMap modelMap){

        User user = userService.getUserById(id);

        String userNewName = (newUserName.equals(""))? user.getName() : newUserName;
        String userNewLogin = (newUserLogin.equals(""))? user.getLogin() : newUserLogin;
        String userNewPassword = (newUserPassword.equals(""))? user.getPassword() : newUserPassword;

        user.setName(userNewName);
        user.setPassword(userNewPassword);

        if (!userService.checkUser(userNewLogin))
            user.setLogin(userNewLogin);

        User u = userService.saveUser(user);
        modelMap.addAttribute("user", u);
        return "redirect:/user?id=".concat(String.valueOf(u.getId()));

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String accessDenied(){
        return "denied";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request,response,authentication);

        return "redirect:/login?logout";
    }
}
