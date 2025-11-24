package com.wangquocthai.java4.lab5.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoShareReport {
    private String videoId;
    private String videoTitle;
    private Long shareCount;
    private Date firstShareDate;
    private Date lastShareDate;
}
