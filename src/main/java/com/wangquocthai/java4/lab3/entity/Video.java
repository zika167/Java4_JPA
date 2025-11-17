package com.wangquocthai.java4.lab3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Video")
public class Video {

    @Id
    @Column(name = "Id")
    private String id; // Không có @GeneratedValue vì Id là VARCHAR

    @Column(name = "Title")
    private String title;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views")
    private Integer views = 0;

    @Column(name = "Description")
    private String description; // Ánh xạ từ LONGTEXT / NVARCHAR(MAX)

    @Column(name = "Active")
    private Boolean active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedDate")
    private Date updatedDate;

    // Quan hệ 1 Video - Nhiều Favorite
    @OneToMany(mappedBy = "video") // Trỏ đến trường "video" trong lớp Favorite
    private List<Favorite> favorites;

    // Quan hệ 1 Video - Nhiều Share
    @OneToMany(mappedBy = "video") // Trỏ đến trường "video" trong lớp Share
    private List<Share> shares;
}