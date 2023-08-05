package org.justmine.spring.common.util;

public class MDCUtils {
    private MDCUtils() {
    }

    public static void put(String key, String value) {
        org.slf4j.MDC.put(key, value);
    }

    public static String get(String key) {
        return org.slf4j.MDC.get(key);
    }

    public static void remove(String key) {
        org.slf4j.MDC.remove(key);
    }

    public static void clear() {
        org.slf4j.MDC.clear();
    }

    public static java.util.Map<String, String> getCopyOfContextMap() {
        return org.slf4j.MDC.getCopyOfContextMap();
    }

    public static void setContextMap(java.util.Map<String, String> contextMap) {
        org.slf4j.MDC.setContextMap(contextMap);
    }

    public static org.slf4j.MDC.MDCCloseable putCloseable(String key, String val) {
        return org.slf4j.MDC.putCloseable(key, val);
    }

}
