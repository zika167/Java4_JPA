package com.wangquocthai.java4.lab5.favorite.entity;

import com.wangquocthai.java4.lab5.user.entity.User;
import com.wangquocthai.java4.lab5.video.entity.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favorite")
public class Favorite {
    @Id
    @Column(name = "Id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;
    @Column
    private Date likeDate;
}
