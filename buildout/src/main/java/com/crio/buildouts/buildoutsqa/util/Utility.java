package com.crio.buildouts.buildoutsqa.util;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utility {
    public static String fileToString(String filename) {
        return util(filename);
    }

    private static String util(String filename) {
        final URL resource = Resources.getResource(filename);
        try {
            return Resources.toString(resource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}

