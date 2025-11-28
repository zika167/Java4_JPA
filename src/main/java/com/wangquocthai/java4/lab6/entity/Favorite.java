package com.wangquocthai.java4.lab6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private Video video;
    
    @Column(name = "LikeDate")
    private LocalDateTime likeDate;
}
