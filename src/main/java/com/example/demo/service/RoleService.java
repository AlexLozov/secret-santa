package com.example.demo.service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.RoleType;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Long count(){
        return roleRepository.count();
    }

    @Transactional
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Optional<Role> findRoleById(Integer id){
        return roleRepository.findById(id);
    }

    public Optional<Role> findRoleByRole(RoleType role){
        return roleRepository.findRoleByRole(role);
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

}
