package com.suminghui.bycar.util;

import java.io.*;
import java.net.*;

public class HttpUtil {
    public static String Get(String urlTemp, String charset) {
        if (charset == null) {
            charset = "UTF-8";
        }
        HttpURLConnection connection = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlTemp); // 把字符串转换为URL请求地址
            connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            StringBuilder sb = new StringBuilder();
//            FileOutputStream fo = new FileOutputStream(_FileName);
//            OutputStreamWriter writer = new OutputStreamWriter(fo, "utf-8");
//            PrintWriter pw = new PrintWriter(writer);
            
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
//                 pw.println(line);
//                 pw.flush();
            }
//            pw.close();
            br.close();// 关闭流
            return sb.toString();
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();// 断开连接
            }
        }
    }
}
