package com.german.ejercicio2springboot.app.service;

import com.german.ejercicio2springboot.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /*Declaracion de métodos*/

    public Iterable<User> findAll();

    public Iterable<User> findAllById(List<Long> ids);

    public Page<User> findAll(Pageable pageable);

    public Optional<User> findById(Long id);

    public User save(User user);

    public void deleteById(Long id);

    public List<User> findByGenderAndAge(String gender, int age);
}
