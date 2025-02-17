package com.example.demo.init;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.RoleType;
import com.example.demo.Entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(userService.count() == 0){

            Role user = roleService.findRoleById(1).orElseThrow(() -> new RuntimeException("Role USER не найден"));
            Role admin = roleService.findRoleById(2).orElseThrow(() -> new RuntimeException("Role ADMIN не найден"));
            Role moderator = roleService.findRoleById(3).orElseThrow(() -> new RuntimeException("Role не найден"));

            userService.saveUser(new User(null, "sasa", "sasa@mail.ru", "12345", user));
            userService.saveUser(new User(null,"dima", "dima@mail.ru", "12345", admin));
            userService.saveUser(new User(null,"vasya", "vasya@mail.ru", "12345", admin));
            userService.saveUser(new User(null,"albert", "albert@mail.ru", "12345", moderator));
        }
    }
}
