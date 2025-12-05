package com.wangquocthai.java4.lab8;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

import java.io.IOException;

public class MessageDecoder implements Decoder.Text<Message> {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void destroy(){}
    @Override
    public void init(EndpointConfig config){}
    @Override
    public Message decode(String json) throws DecodeException {
        try {
            return mapper.readValue(json, Message.class);
        } catch (IOException e) {
            throw new DecodeException(json ,"Ubable to decode");
        }
    }
    @Override
    public boolean willDecode(String json) {
        return json.contains("type")&&json.contains("text");
    }
}
