package com.example.demo.init;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.RoleType;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInit implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.count() == 0){
            roleService.saveRole(new Role(RoleType.USER));
            roleService.saveRole(new Role(RoleType.ADMIN));
            roleService.saveRole(new Role(RoleType.MODERATOR));
        }
    }
}
