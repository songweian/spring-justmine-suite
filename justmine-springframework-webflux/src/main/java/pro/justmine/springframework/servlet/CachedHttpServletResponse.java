package pro.justmine.springframework.servlet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CachedHttpServletResponse extends HttpServletResponseWrapper {
    public CachedHttpServletResponse(HttpServletResponse response) {
        super(response);
    }
}
