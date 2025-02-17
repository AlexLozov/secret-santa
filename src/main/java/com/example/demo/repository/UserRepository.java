package com.example.demo.repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findUserByEmail(String email);
    public boolean existsUserById(Integer id);
    public Optional<User> findUserById(Integer id);

}


