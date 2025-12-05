package com.wangquocthai.java4.lab8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String text;
    private int type;
    private String sender;
    private int count;
}
