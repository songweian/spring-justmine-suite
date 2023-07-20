package org.justmine.spring.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class IOUtils {
    private IOUtils() {
    }

    public static void closeQuietly(java.io.InputStream input) {
        if (input != null) {
            try {
                input.close();
            } catch (java.io.IOException ioe) {
                // ignore
            }
        }
    }

    public static void closeQuietly(java.io.OutputStream output) {
        if (output != null) {
            try {
                output.close();
            } catch (java.io.IOException ioe) {
                // ignore
            }
        }
    }

    public static void closeQuietly(java.io.Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (java.io.IOException ioe) {
                // ignore
            }
        }
    }

    public static void closeQuietly(java.io.Closeable... closeables) {
        if (closeables != null) {
            for (java.io.Closeable closeable : closeables) {
                closeQuietly(closeable);
            }
        }
    }

    public static byte[] readBytesQuietly(java.io.InputStream input) {
        try {
            return new IOUtils().readBytes(input);
        } catch (java.io.IOException ioe) {
            return new byte[0];
        }
    }

    public static String readStringQuietly(java.io.InputStream input) throws IOException {
        return new String(new IOUtils().readBytes(input), StandardCharsets.UTF_8);
    }

    public static String readStringQuietly(java.io.InputStream input, String charsetName) {
        try {
            return new String(new IOUtils().readBytes(input), charsetName);
        } catch (java.io.IOException ioe) {
            return "";
        }
    }

    public static void writeBytes(java.io.OutputStream output, byte[] bytes) throws java.io.IOException {
        try {
            output.write(bytes);
        } finally {
            closeQuietly(output);
        }
    }

    public static void writeString(java.io.OutputStream output, String string) throws java.io.IOException {
        writeBytes(output, string.getBytes(StandardCharsets.UTF_8));
    }

    public static void writeFile(File file, byte[] bytes) throws IOException {
        writeBytes(Files.newOutputStream(file.toPath()), bytes);
    }

    public static void writeFile(java.io.File file, String string) throws java.io.IOException {
        writeString(Files.newOutputStream(file.toPath()), string);
    }

    public static void appendFile(File file, byte[] bytes) throws IOException {
        writeBytes(new FileOutputStream(file, true), bytes);
    }

    public static void appendFile(java.io.File file, String string) throws java.io.IOException {
        writeString(new FileOutputStream(file, true), string);
    }

    public byte[] readBytes(java.io.InputStream input) throws java.io.IOException {
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        } finally {
            closeQuietly(output);
        }
    }

}
