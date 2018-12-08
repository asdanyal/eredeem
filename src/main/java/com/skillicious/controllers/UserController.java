package com.skillicious.controllers;

import com.skillicious.domain.User;
import com.skillicious.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/findAll")
    private List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/delete-user/{}")
    private void deleteUser(@PathVariable("id") String id){
         userRepository.deleteById(id);
    }

}
