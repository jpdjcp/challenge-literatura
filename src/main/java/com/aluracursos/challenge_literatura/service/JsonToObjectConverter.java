package com.aluracursos.challenge_literatura.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToObjectConverter {

    public static <T> T convert(String json, Class<T> clase) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
