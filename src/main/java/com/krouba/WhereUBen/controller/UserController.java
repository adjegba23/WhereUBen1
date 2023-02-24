package com.krouba.WhereUBen.controller;

import com.krouba.WhereUBen.model.User;
import com.krouba.WhereUBen.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Mono<User>> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Mono<User>> getUserById(@PathVariable String id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Flux<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<User>> updateUser(@PathVariable String id,  @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void  deleteUser (@PathVariable String id){
         userService.deleteUser(id).subscribe();

    }
}
