package com.wangquocthai.java4.lab3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`Share`") // Dùng ` ` vì Share có thể là từ khóa
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng
    @Column(name = "Id")
    private Long id;

    @Column(name = "Emails")
    private String emails; // Ánh xạ từ LONGTEXT / NVARCHAR(MAX)

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ShareDate")
    private Date shareDate = new Date();

    // Quan hệ Nhiều Share - 1 User
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    // Quan hệ Nhiều Share - 1 Video
    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;
}