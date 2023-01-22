package com.krouba.WhereUBen.service;

import com.krouba.WhereUBen.model.User;
import com.krouba.WhereUBen.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
        return userRepository.deleteById(id);

    }

    public Mono<User> updateUser(final String id, final User user){
        return userRepository.save(user);
    }


}
