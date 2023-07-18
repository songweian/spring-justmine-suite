package org.justmine.spring.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

import static org.justmine.spring.common.HttpHeaders.X_REQUEST_ID;

/**
 * CopyHttpServletRequestHeaderInterceptor
 */
public class CopyHttpServletRequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(X_REQUEST_ID, UUID.randomUUID().toString());
    }

}
