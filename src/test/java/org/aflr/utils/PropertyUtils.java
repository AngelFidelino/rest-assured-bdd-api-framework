package org.aflr.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static Properties props;
    static {
        props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/properties.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
