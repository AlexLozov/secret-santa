package com.example.demo.rest;

import com.example.demo.Entity.DTO.UserRequestDTO;
import com.example.demo.Entity.DTO.UserResponseDTO;
import com.example.demo.Entity.DTO.UserUpdateRequestDTO;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.RoleType;
import com.example.demo.Entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    public final UserService userService;
    public final RoleService roleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        User user = new User(
                userRequestDTO.name(),
                userRequestDTO.email(),
                userRequestDTO.password(),
                roleService.findRoleById(1).orElseThrow(() -> new EntityNotFoundException("Role USER не найден")));
        User createdUser = userService.saveUser(user);

        return new UserResponseDTO(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail(),
                createdUser.getRole().getType()
        );
    }



    @GetMapping
    public List<UserResponseDTO> userList(){
        return userService.findAllUsers().stream()
                .map(user -> new UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole().getType()))
                .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id){
        return userService.findUserById(id)
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole().getType()))
                .map(ResponseEntity::ok) // возвращаем 200 OK с данными
                .orElseGet(() -> ResponseEntity.notFound().build()); // если не найден - возвращаем 404 Not Found
    }



    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id,
                                                      @RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
        return userService.findUserById(id)
                .map(user -> {
                    user.setName(userUpdateRequestDTO.name());
                    user.setPassword(userUpdateRequestDTO.password());
                    userService.saveUser(user);
                    return new UserResponseDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getRole().getType()
                    );
                })
                .map(ResponseEntity::ok) // возвращаем обновлённый объект с 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // если не найден - возвращаем 404 Not Found
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        if (userService.existsUserById(id)) {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build(); // Возвращаем статус 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем статус 404 Not Found
        }
    }
}
