package com81.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls) {
        T javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return javaResult;
    }

}
