package pro.justmine.common;

import com.google.gson.Gson;
import pro.justmine.common.gson.GsonCustomizer;
import pro.justmine.common.gson.JustGson;

import java.util.ServiceLoader;

public class JustJson {
    public static Gson GSON;

    static {
        ServiceLoader<GsonCustomizer> gsonCustomizers = ServiceLoader.load(GsonCustomizer.class);
        JustGson.Builder builder = JustGson.builder();
        for (GsonCustomizer gsonCustomizer : gsonCustomizers) {
            builder.addCustomizer(gsonCustomizer);
        }
        GSON = builder.build();
    }

    public static Gson getGson() {
        return GSON;
    }

    public static String toJsonString(Object o) {
        return GSON.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
