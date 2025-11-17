package com.wangquocthai.java4.lab3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[User]") // Dùng ` ` hoặc `[]` vì User là từ khóa SQL
public class User {

    @Id
    @Column(name = "Id")
    private String id; // Không có @GeneratedValue vì Id là VARCHAR (tự nhập)

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Admin")
    private Boolean admin = false; // Ánh xạ từ BOOLEAN / BIT

    @Temporal(TemporalType.TIMESTAMP) // Chú thích cho kiểu java.util.Date
    @Column(name = "CreatedDate")
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedDate")
    private Date updatedDate;

    // Quan hệ 1 User - Nhiều Favorite
    @OneToMany(mappedBy = "user") // "mappedBy" trỏ đến TÊN TRƯỜNG "user" trong lớp Favorite
    private List<Favorite> favorites;

    // Quan hệ 1 User - Nhiều Share
    @OneToMany(mappedBy = "user") // "mappedBy" trỏ đến TÊN TRƯỜNG "user" trong lớp Share
    private List<Share> shares;

}