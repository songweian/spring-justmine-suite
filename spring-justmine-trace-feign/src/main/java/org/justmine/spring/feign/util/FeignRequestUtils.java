package org.justmine.spring.feign.util;

import feign.RequestTemplate;

public class FeignRequestUtils {
    private FeignRequestUtils() {
    }

    public static boolean isTextBasedRequest(RequestTemplate requestTemplate) {
        // if is json or xml, then it is text based
        return requestTemplate.headers().containsKey("Content-Type") &&
                (requestTemplate.headers().get("Content-Type").contains("json") ||
                        requestTemplate.headers().get("Content-Type").contains("xml"));
    }
}
