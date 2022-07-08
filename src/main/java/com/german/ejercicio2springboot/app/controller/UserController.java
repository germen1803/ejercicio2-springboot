package com.german.ejercicio2springboot.app.controller;

import com.german.ejercicio2springboot.app.entity.User;
import com.german.ejercicio2springboot.app.repository.UserRepository;
import com.german.ejercicio2springboot.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class UserController {

    /*Inyeccion de dependencias*/
    @Autowired
    private UserService userService;

    //Crear usuario
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //Obtener usuario o usuarios
    @GetMapping("/getUsers/{ids}")
    public ResponseEntity<?> read(@PathVariable List<Long> ids){
        Iterable<User> oUser = userService.findAllById(ids);

        return ResponseEntity.ok(oUser);
    }

    //Actualizar usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId) {
        Optional<User> user = userService.findById(userId);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }

        user.get().setName(userDetails.getName());
        user.get().setLastName(userDetails.getLastName());
        user.get().setAge(userDetails.getAge());
        user.get().setGender(userDetails.getGender());
        user.get().setEmail(userDetails.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
    }

    //Borrar usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
        Optional<User> user = userService.findById(userId);

        if(!userService.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(userId);

        return ResponseEntity.ok().build();

    }

    //Obtener todos los usuarios
    @GetMapping("/getUsers")
    public List<User> readAll() {
        List<User> users = StreamSupport
                            .stream(userService.findAll().spliterator(),false)
                            .collect(Collectors.toList());

        return users;
    }

    //Obtener usuarios filtrados por gender y age
    @GetMapping("/getUsers/")
    public ResponseEntity<List<User>> filtered(@RequestParam String gender, @RequestParam int age) {
        return new ResponseEntity<>(userService.findByGenderAndAge(gender, age) , HttpStatus.OK);
    }

}
