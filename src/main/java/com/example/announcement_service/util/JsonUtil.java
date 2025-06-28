package com.example.announcement_service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.io.Reader;

public class JsonUtil {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .findAndRegisterModules()
            .configure(com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);

    public static <T> T read(Reader reader, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(reader, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении JSON", e);
        }
    }

    public static String write(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации объекта в JSON", e);
        }
    }
}
