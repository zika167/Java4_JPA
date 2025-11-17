package com.wangquocthai.java4.lab3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng (cho AUTO_INCREMENT / IDENTITY)
    @Column(name = "Id")
    private Long id; // Khớp với BIGINT

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LikeDate")
    private Date likeDate = new Date();

    // Quan hệ Nhiều Favorite - 1 User
    @ManyToOne
    @JoinColumn(name = "UserId") // Ánh xạ cột khóa ngoại "UserId" trong bảng Favorite
    private User user; // Tên trường này phải khớp với "mappedBy" trong lớp User

    // Quan hệ Nhiều Favorite - 1 Video
    @ManyToOne
    @JoinColumn(name = "VideoId") // Ánh xạ cột khóa ngoại "VideoId" trong bảng Favorite
    private Video video; // Tên trường này phải khớp với "mappedBy" trong lớp Video
}