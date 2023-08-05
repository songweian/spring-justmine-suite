package pro.justmine.common.annotation;

public @interface Ordered {
    int getOrder() default 0;

    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
}
