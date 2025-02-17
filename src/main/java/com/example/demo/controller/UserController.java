package com.example.demo.controller;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.RoleType;
import com.example.demo.Entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
//-------------------------------------------
    @GetMapping
    public String getAllUsers(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("allUsers", users);
        return "users";
    }
//-------------------------------------------

    @GetMapping("/{id}")
    public String userById(Model model, @PathVariable Integer id){
        User user = userService.findUserById(id).orElseThrow();
        model.addAttribute("getUser", user);
        return "userById";
    }

//-------------------------------------------
    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable Integer id, Model model){
        User user = userService.findUserById(id).orElseThrow();
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("updateUser", user);
        model.addAttribute("allRoles", roles);
        return "updateUserForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/users";
    }





//-------------------------------------------



}
