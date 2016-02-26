package com.suminghui.bycar.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertyUtil {

    private static Properties p = null;
    static {
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("app.properties");
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        String value = StringUtil.EMPTY;
        try {
            key = new String(key.getBytes("UTF-8"), "ISO8859_1");
            value = p.getProperty(key);
            value = new String(value.getBytes("ISO8859_1"), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }

        return value;
    }

    public static String getStaticUrl() {
        return p.getProperty("static_url");
    }
}
