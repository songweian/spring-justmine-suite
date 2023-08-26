package pro.justmine.springframework.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public CachedHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
}
