package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new ResourceNotFoundException("User not found with id: " + id);
    }

    @Override
    public List<User> findTopKOldestUser(long k) {
//        Pageable pageable = PageRequest.of(0,(int)k);
        return repository.customQueryMethod((int) k);
    }

}
