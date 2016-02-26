package com.suminghui.bycar.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

    public static String getPageTitleInfo(String url) {
        
        String pageContent = StringUtil.EMPTY;
        pageContent = HttpUtil.Get(url, "UTF-8");
        if (url.contains("163.com") || url.contains("news.qq.com") || url.contains("sohu.com") || url.contains("sina.com")) {
            pageContent = HttpUtil.Get(url, "gb2312");
        }

        Pattern p = Pattern.compile("<title>(?<title>.+?)</title>");
        Matcher m = p.matcher(pageContent);
        
        String title = StringUtil.EMPTY;

        if (m.find()) {
            title = m.group("title");
        }

        return title;
    }

//    private static String getCharset(String pageContent) {
//        Pattern p = Pattern.compile("<meta (.?) charset=(?<charset>.?)\" />");
//        Matcher m = p.matcher(pageContent);
//        if (m.find()) {
//            return m.group("charset");
//        }
//        return "UTF-8";
//    }

    public static void main(String[] args) {
        //String pageContent = HttpUtil.Get("http://www.163.com/", null);
        String title = getPageTitleInfo("http://news.163.com/15/1228/15/BBUC3JP900014PRF.html");
        System.out.println(title);
    }

    /**
     * 字符串编码转换的实现方法
     * 
     * @param str
     *            待转换编码的字符串
     * @param newCharset
     *            目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
}
