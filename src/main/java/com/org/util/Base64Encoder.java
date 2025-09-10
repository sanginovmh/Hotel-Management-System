package com.org.util;

import java.util.Base64;

public class Base64Encoder {
    private Base64Encoder() {
    }

    public static String encode(String string) {
        return Base64
                .getEncoder()
                .encodeToString(string
                        .getBytes());
    }
}
