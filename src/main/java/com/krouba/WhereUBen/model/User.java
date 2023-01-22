package com.krouba.WhereUBen.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.lang.annotation.Documented;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Getter @Setter
@Document(collection = "user_tbl")
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private Role role;
}

