package com.wangquocthai.java4.lab4.share.entity;

import com.wangquocthai.java4.lab4.user.entity.User;
import com.wangquocthai.java4.lab4.video.entity.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Share")
@NamedQueries({
    @NamedQuery(name = "Share.findVideosSharedIn2024",
        query = "SELECT DISTINCT s.video FROM Share s WHERE YEAR(s.shareDate) = 2024 ORDER BY s.shareDate DESC"),
    @NamedQuery(name = "Share.getVideoShareReport",
        query = """
                SELECT new com.wangquocthai.java4.lab4.share.dto.VideoShareReport(s.video.id, s.video.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate))
                FROM Share s 
                GROUP BY s.video.id, s.video.title 
                ORDER BY COUNT(s) DESC
                """)
})
public class Share {
    @Id
    @Column(name = "Id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;
    @Column(name = "Emails")
    private String email;
    @Column(name = "ShareDate")
    private Date shareDate;
}
