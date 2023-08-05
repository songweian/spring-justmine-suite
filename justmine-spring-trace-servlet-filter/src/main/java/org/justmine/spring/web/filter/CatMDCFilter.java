package org.justmine.spring.web.filter;

import com.dianping.cat.Cat;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class CatMDCFilter implements Filter {
    private static final String TRACE_ID = "X-CAT-TRACE-ID";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String traceId = httpRequest.getHeader(TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
        }
        Cat.logEvent("TraceId", traceId);
        try {
            Cat.logEvent("Server", httpRequest.getLocalAddr());
            Cat.logEvent("App", "YourAppName");
            chain.doFilter(request, response);
        } finally {
            Cat.logEvent("Success", "true");
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}
