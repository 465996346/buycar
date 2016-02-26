package com.suminghui.bycar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class I18NPropertyUtil {

    private static Properties p = null;
    static {
        InputStream in = null;
        try {
            in = I18NPropertyUtil.class.getClassLoader().getResourceAsStream("I18NMessage.properties");
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            p = new Properties();
            p.load(bf);
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
        return p.getProperty(key);
    }
}
