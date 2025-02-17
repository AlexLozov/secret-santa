package com.example.demo.Entity;

import com.example.demo.Entity.DTO.UserRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = "role")
@EqualsAndHashCode(exclude = "role")
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    @Size(min=4, max= 64)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name="password", nullable = false)
    @Size(min=3, max=10)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    @NotNull
    private Role role;


    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
