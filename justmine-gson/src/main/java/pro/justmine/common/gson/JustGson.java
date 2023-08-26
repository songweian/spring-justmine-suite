package pro.justmine.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import pro.justmine.common.JustOrderUtils;

import java.util.ArrayList;
import java.util.List;

public class JustGson {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final GsonBuilder builder;

        private final List<GsonCustomizer> customizers = new ArrayList<>();

        public Builder() {
            builder = new GsonBuilder();
        }

        public Builder(GsonBuilder builder) {
            this.builder = builder;
        }

        public Builder addCustomizer(GsonCustomizer customizer) {
            customizers.add(customizer);
            return this;
        }

        public Gson build() {
            AnnotationAwareOrderComparator.sort(customizers);
            for (GsonCustomizer customizer : customizers) {
                customizer.customize(builder);
            }
            return builder.create();
        }
    }
}
