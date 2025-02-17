package com.example.demo.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(RoleType role){
        this.role = role;
    }

    public String getType(){
        return role.name();
    }

//    public void addUser(User user) {
//        if (users == null) {
//            users = new ArrayList<>();
//        }
//        users.add(user);
//        user.setRole(this);
//    }
}
