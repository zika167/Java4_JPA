package com.wangquocthai.java4.lab5.logs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Logs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Url", length = 500)
    private String url;
    
    @Column(name = "Time")
    private LocalDateTime time;
    
    @Column(name = "Username", length = 50)
    private String username;
}
