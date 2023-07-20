package org.justmine.spring.web.util;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachingHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] cachedRequestBody;

    public CachingHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedRequestBody == null) {
            cachedRequestBody = IOUtils.toByteArray(super.getInputStream());
        }
        return new CachedServletInputStream(new ByteArrayInputStream(cachedRequestBody));
    }

    private static class CachedServletInputStream extends ServletInputStream {
        private final InputStream cachedInputStream;

        public CachedServletInputStream(InputStream cachedInputStream) {
            this.cachedInputStream = cachedInputStream;
        }

        @Override
        public int read() throws IOException {
            return cachedInputStream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }
    }
}
