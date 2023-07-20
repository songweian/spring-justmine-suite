package org.justmine.spring.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.lang.reflect.Type;

public class JsonUtils {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        return gson.fromJson(reader, clazz);
    }

    public static <T> T fromJson(Reader reader, Type type) {
        return gson.fromJson(reader, type);
    }

    public static <T> T fromJson(Reader reader, TypeToken<T> typeToken) {
        return gson.fromJson(reader, typeToken.getType());
    }

    public static <T> T fromJson(JsonReader reader, Class<T> clazz) {
        return gson.fromJson(reader, clazz);
    }

    public static <T> T fromJson(JsonReader reader, Type type) {
        return gson.fromJson(reader, type);
    }


}
