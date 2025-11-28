package com.wangquocthai.java4.lab6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Videos")
public class Video {
    @Id
    @Column(name = "Id", length = 20)
    private String id;
    
    @Column(name = "Title", length = 255, nullable = false)
    private String title;
    
    @Column(name = "Poster", length = 255)
    private String poster;
    
    @Column(name = "Views")
    private Integer views = 0;
    
    @Column(name = "Description", length = 500)
    private String description;
    
    @Column(name = "Active")
    private Boolean active = true;
}
