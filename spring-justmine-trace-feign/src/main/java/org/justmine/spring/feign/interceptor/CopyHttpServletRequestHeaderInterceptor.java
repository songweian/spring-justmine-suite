package org.justmine.spring.feign.interceptor;

import com.google.common.collect.Sets;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * CopyHttpServletRequestHeaderInterceptor
 */

public class CopyHttpServletRequestHeaderInterceptor implements RequestInterceptor {
    private final Config config;

    public CopyHttpServletRequestHeaderInterceptor(Config config) {
        this.config = config;
    }

    @Override
    public void apply(RequestTemplate template) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement().toLowerCase();
            if (config.getIncludeHeaders().contains("*") || config.getIncludeHeaders().contains(headerName)) {
                if (!config.getExcludeHeaders().contains(headerName)) {
                    Enumeration<String> headers = request.getHeaders(headerName);
                    while (headers.hasMoreElements()) {
                        String headerValue = headers.nextElement();
                        template.header(headerName, headerValue);
                    }
                }
            }
        }
    }

    @Data
    @Builder
    public static class Config {
        /**
         * 要包含的请求头
         * * 表示所有
         */
        private Set<String> includeHeaders = Sets.newHashSet("*");
        /**
         * 要排除的请求头
         * 空集合表示不排除
         */
        private Set<String> excludeHeaders = new HashSet<>();
    }
}
