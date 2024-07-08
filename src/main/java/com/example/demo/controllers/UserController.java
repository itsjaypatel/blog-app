package com.example.demo.controllers;

import java.util.List;

import com.example.demo.assembler.UserModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    private final UserModelAssembler assembler;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserModelAssembler assembler){
        this.userService = userService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> create(@RequestBody User user){
        log.info("Creating user: {}", user);
        User createdUser = userService.createUser(user);

        return ResponseEntity.created(linkTo(methodOn(UserController.class).getById(createdUser.getId())).toUri()).body(assembler.toModel(createdUser));
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getAll(){
        List<EntityModel<User>> users = userService.getAllUsers().stream().map(assembler::toModel).toList();
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> getById(@PathVariable long id){
        return assembler.toModel(userService.getUserById(id));
    }

    @GetMapping("/oldest/{k}")
    public List<User> findTopKOldestUser(@PathVariable long k) {
        return userService.findTopKOldestUser(k);
    }
}
