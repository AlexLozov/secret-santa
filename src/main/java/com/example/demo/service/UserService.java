package com.example.demo.service;

import com.example.demo.Entity.DTO.UserRequestDTO;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }

    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }


//    public User saveUser(UserRequestDTO userRequestDTO){
//        userRepository.findUserByEmail(userRequestDTO.email()).ifPresent(user -> {
//            throw new IllegalArgumentException("Такой пользователь уже существует");
//        });
//
//        User user = new User(userRequestDTO.name(), userRequestDTO.email(), userRequestDTO.password(), roleService.findRoleById(1).orElseThrow());
//        return userRepository.save(user);
//    }

    @Transactional
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public Long count(){
        return userRepository.count();
    }

    public boolean existsUserById(Integer id){
        return userRepository.existsUserById(id);
    }

    @Transactional
    public User updateUser(User user){
        User updatedUser = userRepository.findUserById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        if(!user.getName().isBlank()){
            updatedUser.setName(user.getName());
        }

        if(!user.getPassword().isBlank()){
            updatedUser.setPassword(user.getPassword());
        }

//        if(!user.getEmail().isBlank()){
//
//        }

        if(user.getRole() != null){
            updatedUser.setRole(user.getRole());
        }

        return userRepository.save(updatedUser);
    }

}
