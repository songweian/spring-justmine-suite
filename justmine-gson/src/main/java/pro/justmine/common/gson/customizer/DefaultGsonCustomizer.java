package pro.justmine.common.gson.customizer;

import com.google.gson.GsonBuilder;
import pro.justmine.common.Order;
import pro.justmine.common.gson.GsonCustomizer;
import pro.justmine.common.gson.exclusion.IgnoreAnnotationStrategy;
import pro.justmine.common.gson.exclusion.IgnoreDeserializeAnnotationStrategy;
import pro.justmine.common.gson.exclusion.IgnoreSerializeAnnotationStrategy;

public class DefaultGsonCustomizer implements GsonCustomizer, Order {

    public static final int ORDER = 0;

    @Override
    public void customize(GsonBuilder gsonBuilder) {
        gsonBuilder.addSerializationExclusionStrategy(new IgnoreAnnotationStrategy());
        gsonBuilder.addDeserializationExclusionStrategy(new IgnoreAnnotationStrategy());

        gsonBuilder.addSerializationExclusionStrategy(new IgnoreSerializeAnnotationStrategy());
        gsonBuilder.addSerializationExclusionStrategy(new IgnoreDeserializeAnnotationStrategy());
    }

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "order=" + getOrder() + '}';
    }
}
