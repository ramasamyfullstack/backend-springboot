package com.ramasamy.myspringproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramasamy.myspringproject.entity.UserEntity;
import com.ramasamy.myspringproject.exception.ResourceNotFoundException;
import com.ramasamy.myspringproject.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // @GetMapping
    // public String getUser() {
    // return "Hi Ram!!!";
    // }

    // @GetMapping
    // public List<User> getUser() {
    // return Arrays.asList(new User(1, "Kamal", "kamal@gmail.com", "Chennai"),
    // new User(1, "Kamal", "kamal@gmail.com", "Chennai"),
    // new User(1, "Kamal", "kamal@gmail.com", "Chennai"));
    // }
    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<UserEntity> getUser() {
        return repo.findAll();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity myuser) {
        // System.out.println("User Data : " + myuser.getName() + " , " +
        // myuser.getEmail());
        return repo.save(myuser);
    }

    // @GetMapping("/{id}")
    // public Optional<UserEntity> getUserById(@PathVariable Integer id) {
    // return repo.findById(id);
    // }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id : " + id));
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity myusers) {

        UserEntity userData = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id : " + id));
        userData.setName(myusers.getName());
        userData.setEmail(myusers.getEmail());
        userData.setCity(myusers.getCity());
        return repo.save(userData);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        UserEntity userData = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this id : " + id));
        repo.delete(userData);
        return ResponseEntity.ok().build();
    }

}
