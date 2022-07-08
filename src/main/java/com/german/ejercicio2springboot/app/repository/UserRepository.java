package com.german.ejercicio2springboot.app.repository;


import com.german.ejercicio2springboot.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGenderAndAge(String gender, int age);
}
