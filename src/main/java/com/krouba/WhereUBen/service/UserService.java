package com.krouba.WhereUBen.service;

import com.krouba.WhereUBen.model.User;
import com.krouba.WhereUBen.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
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

    public Mono<Void> deleteUser(String id){
        return userRepository.deleteById(id).switchIfEmpty(Mono.error(new Exception("User not found")));

    }

    public Mono<User> updateUser( User user, String id){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("User not found")))
                .map(olderUser ->{
                    if(user.getEmail() != null) olderUser.setEmail(user.getEmail());
                    if(user.getRole() != null) olderUser.setRole(user.getRole());
                    if(user.getUsername() != null) olderUser.setUsername(user.getUsername());
                    if(user.getPassword() != null) olderUser.setPassword(user.getPassword());
                    if(user.getFirstname() != null) olderUser.setFirstname(user.getFirstname());
                    if(user.getLastname() != null) olderUser.setLastname(user.getLastname());
                    return olderUser;
                })
                .flatMap(userRepository::save);



    }


}
