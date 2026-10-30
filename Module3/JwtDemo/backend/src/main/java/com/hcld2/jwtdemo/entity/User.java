package com.hcld2.jwtdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity 
@Table (name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @id
    
    private Long id;
    private String username;
    private String password;

}
