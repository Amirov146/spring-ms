package com.example.main.controller;

import com.example.main.serviceImpl.UserServiceImpl;
import com.example.main.model.UsersRoles;
import com.example.main.repository.UsersRolesRepository;
import com.example.main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UsersRolesRepository users_roles_repository;

    @GetMapping("/all")
    @ResponseBody
    public List<User> users(){
        return userService.findAll();
    }


    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "reg";
    }

    @PostMapping("/register")
    @ResponseBody
    public String userSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
            if(userService.existsUserByUsername(user.getUsername()) == true){
                return "<script> alert(\"User with this login already exists! Try again \");" +
                        " window.location.replace(\"/user\"); " +
                        "</script>";
            }
            else if(userService.existsUserByEmail(user.getEmail())==true){
                return "<script> alert(\"User with this email already exists! Try again \");" +
                        "window.location.replace(\"/user\"); " +
                        "</script>";
            }
            else {
                userService.save(user);
                users_roles_repository.save(new UsersRoles(new UsersRoles.Users_Roles_ID(user.getId(),1L)));
                return "<script> alert(\"User was signed up successfully.Now please Log in \");" +
                        "window.location.replace(\"/\");" +
                         "</script>";
            }
    }

    @GetMapping("/user/{username}")
    @ResponseBody
    public User findUserByLogin(@PathVariable("username") String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String loginPage(){
        return "index";
    }


    @GetMapping("/user")
    public String userPage(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}
