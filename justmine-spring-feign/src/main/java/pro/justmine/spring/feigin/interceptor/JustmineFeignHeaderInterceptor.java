package pro.justmine.spring.feigin.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.List;

public class JustmineFeignHeaderInterceptor implements RequestInterceptor {
    private final List<String> headerNames;

    public JustmineFeignHeaderInterceptor(List<String> headerNames) {
        this.headerNames = headerNames;
    }

    @Override
    public void apply(RequestTemplate template) {
        headerNames.forEach(headerName -> {
            if (template.headers().containsKey(headerName)) {
                template.header(headerName, template.headers().get(headerName));
            }
        });
    }
}
