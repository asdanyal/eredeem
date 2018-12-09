package com.skillicious.controllers;

import com.skillicious.domain.User;
import com.skillicious.repository.UserRepository;
import com.skillicious.response.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api( value = "User Service REST End Points", description = "Shows Users Info")
public class UserController {



    @Resource
    private UserRepository userRepository;

    @ApiOperation("Returns List Of All Users")
    @GetMapping("/findAll")
    private List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @ApiOperation("Delete the User")
    @DeleteMapping("/delete-user/{id}")
    private void deleteUser(@PathVariable("id") String id){
         userRepository.deleteById(id);
    }

    @ApiOperation("Creates New User")
    @PostMapping("/add")
    private ResponseEntity<?> createUser(@RequestBody User request, BindingResult errors){

        User user = new User();

        if(errors.hasErrors()) {
            return new ResponseEntity<>(new Message(errors.getAllErrors().get(0).getDefaultMessage()), BAD_REQUEST);
        }

        user.setId(RandomStringUtils.randomNumeric(10));
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setUserName(request.getUserName().trim().toLowerCase());
        user.setPassword(request.getPassword().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setLinkedIn(request.getLinkedIn());

        userRepository.save(user);
        return new ResponseEntity<>(user, CREATED);

    }

    @ApiOperation("Edit Current User Information")
    @PutMapping("/edit/{id}")
    private ResponseEntity<?> editUser(@RequestBody User request,@PathVariable("id") String id, BindingResult errors){

        User user = new User();

        if(errors.hasErrors()) {
            return new ResponseEntity<>(new Message(errors.getAllErrors().get(0).getDefaultMessage()), BAD_REQUEST);
        }

        user.setId(id);
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setUserName(request.getUserName().trim().toLowerCase());
        user.setPassword(request.getPassword().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setLinkedIn(request.getLinkedIn());

        userRepository.save(user);
        return new ResponseEntity<>(user, OK);

    }

}
