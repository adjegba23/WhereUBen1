package com.krouba.WhereUBen.service;

import com.krouba.WhereUBen.model.User;
import com.krouba.WhereUBen.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> saveUser(User user){
        return userRepository.save(user);
    }

    public Mono<User> getUser(String id){
        return userRepository.findById(id);
    }

    public Flux<User> getAllUsers(){return userRepository.findAll();}

    public Mono<Void> deleteUser(String id){ return userRepository.deleteById(id);}

    public Mono<User> updateUser(String id,  User user){
        User userToUpdate = userRepository.findById(id).block();
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());

        return userRepository.save(userToUpdate);
    }
}
