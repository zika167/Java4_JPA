package com.wangquocthai.java4.lab6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Id", length = 20)
    private String id;
    
    @Column(name = "Password", length = 50, nullable = false)
    private String password;
    
    @Column(name = "Fullname", length = 50, nullable = false)
    private String fullname;
    
    @Column(name = "Email", length = 50, nullable = false)
    private String email;
    
    @Column(name = "Admin")
    private Boolean admin = false;
}
