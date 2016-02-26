package com.suminghui.bycar.common;

import java.beans.PropertyDescriptor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public static final String EMPTY = "";

    public static boolean isEmpty(String data) {
        if (data == null || data.equals("")) {
            return true;
        }
        return false;
    }

    public static String htmlEncode(String txt) {
        if (txt != null) {
            txt = replace(txt, "&", "&amp;");
            txt = replace(txt, "&amp;amp;", "&amp;");
            txt = replace(txt, "&amp;quot;", "&quot;");
            txt = replace(txt, "\"", "&quot;");
            txt = replace(txt, "&amp;lt;", "&lt;");
            txt = replace(txt, "<", "&lt;");
            txt = replace(txt, "&amp;gt;", "&gt;");
            txt = replace(txt, ">", "&gt;");
            txt = replace(txt, "&amp;nbsp;", "&nbsp;");

        }
        return txt;
    }

    public static String[] split(String strSource, String strDiv) {
        int arynum = 0, intIdx = 0, intIdex = 0;
        int div_length = strDiv.length();
        if (strSource.compareTo("") != 0) {
                if (strSource.indexOf(strDiv) != -1) {
                        intIdx = strSource.indexOf(strDiv);
                        for (int intCount = 1; ; intCount++) {
                                if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                                        arynum = intCount;
                                }
                                else {
                                        arynum += 2;
                                        break;
                                }
                        }
                }
                else {
                        arynum = 1;
                }
        }
        else {
                arynum = 0;

        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];

        if (strSource.compareTo("") != 0) {
                if (strSource.indexOf(strDiv) != -1) {
                        intIdx = strSource.indexOf(strDiv);
                        returnStr[0] = strSource.substring(0, intIdx);
                        for (int intCount = 1; ; intCount++) {
                                if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                                        intIdex = strSource.indexOf(strDiv, intIdx + div_length);
                                        returnStr[intCount] = strSource.substring(intIdx + div_length,
                                                intIdex);
                                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                                }
                                else {
                                        returnStr[intCount] = strSource.substring(intIdx + div_length,
                                                strSource.length());
                                        break;
                                }
                        }
                }
                else {
                        returnStr[0] = strSource.substring(0, strSource.length());
                        return returnStr;
                }
        }
        else {
                return returnStr;
        }
        return returnStr;
}

    public static String doWithNull(Object o) {
        if(o == null) {
            return "";
        } else{
            String returnValue = o.toString();
            if(returnValue.equalsIgnoreCase("null")) {
                return "";
            } else {
                return returnValue.trim();
            }
        }

    }

    public static String replace(String str, String strSub, String strRpl) {
        String[] tmp = split(str, strSub);
        String returnstr = "";
        if (tmp.length != 0) {
            returnstr = tmp[0];
            for (int i = 0; i < tmp.length - 1; i++) {
                returnstr = doWithNull(returnstr) + strRpl + tmp[i + 1];
            }
        }
        return doWithNull(returnstr);
    }

    // 数字正则
    private static final Pattern RE_NUMBER = Pattern.compile("[0-9]+");
    // 字符正则
    private static final Pattern RE_CHARACTER = Pattern.compile("\\w+");

    private static final Pattern Html_TAG = Pattern.compile("<style.*?</style>|<script.*?</script>|<([^>]*)>");
    // 千分位显示
    private static final DecimalFormat THOUSANDS_TAG = new DecimalFormat("#,###");

    /**
     * 将一个字符数组中每两个字符中间加入一个字符
     * 
     * @param strs
     *            要处理的字符数组
     * @param s
     *            要加入的字符
     * @return
     */
    public static String join(String[] strs, String s) {

        if (null == strs)
            return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i != strs.length - 1)
                sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 将一个字符串分割成一个字符数组
     * 
     * @param str
     *            要分割的字符串
     * @param separatorChar
     *            分隔符
     * @return
     */
    public static String[] split(String str, char separatorChar) {

        return StringUtils.split(str, separatorChar);

    }

    /**
     * 将一个字符串通过指定字符分割后存入list列表
     * 
     * @param str
     *            要转换的字符串
     * @param separatorChar
     *            分隔符
     * @return
     */
    public static List<String> splitToList(String str, char separatorChar) {//
        if (StringUtil.isBlank(str)) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (String s : StringUtil.split(str, separatorChar)) {
            list.add(s);
        }
        return list;
    }

    /**
     * 判断字符串 是不是为空
     * 
     * @param str
     *            要判断的字符串
     * @return
     */
    public static boolean isBlank(String str) {
        if (null == str)
            return true;
        if ("".equals(str.replaceAll("　", " ").trim()))
            return true;
        return false;
    }

    /**
     * 获取随机长度的一个字符串
     * 
     * @param j
     *            要获得的字符串长度
     * @return
     */
    public static String toRandom(int j) {

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < j; i++) {
            Random r = new Random();
            int n = r.nextInt(3);
            if (n == 0) {
                s.append(r.nextInt(9));
            } else if (n == 1) {
                s.append((char) ('a' + Math.random() * 26));
            } else {
                s.append((char) ('A' + Math.random() * 26));
            }
        }

        return s.toString();
    }

    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /**
     * 获取随机长度的一个字符串
     * 
     * @param length
     *            要获得的字符串长度
     * @return
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[new Random().nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * 判断字符串是不是符合邮箱格式
     * 
     * @param mailAddr
     *            要校验的字符串
     * @return 是返回true 否则 false
     */
    public static boolean isMailAddr(String mailAddr) {

        String check = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mailAddr);
        return matcher.matches();
    }

    /**
     * 判断字符串是不是数字
     * 
     * @param str
     *            要校验的字符串
     * @return
     */
    public static boolean isNumber(String str) {
        return (str != null) && RE_NUMBER.matcher(str).matches();
    }

    /**
     * 将一个字符串列表转换为字符串并用指定的符号分割
     * 
     * @param list
     *            要转换的字符串列表
     * @param separatorChar
     *            指定的分割符
     * @return
     */
    public static String getListToString(List<String> list, char separatorChar) {
        if (null != list && list.size() > 0) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i != list.size() - 1) {
                    sb.append(separatorChar);
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 取出html里所有标签和<scritp><style>内容
     * 
     * @param html
     *            html内容
     * @param replace
     *            替换内容
     * @return
     */
    public static String getHtmlToText(String html, String replace) {
        Matcher m = Html_TAG.matcher(html);
        return m.replaceAll(replace);
    }

    /**
     * 判断是否只包含字符
     * 
     * @param s
     * @return
     */
    public static boolean regularCharacter(String s) {
        return RE_CHARACTER.matcher(s).matches();
    }

    /**
     * 解析bean对象属性
     * 
     * @param p
     * @return
     */
    public static String getDisplayName(PropertyDescriptor p) {
        if (null != p && null != p.getReadMethod()) {
            if (p.getPropertyType() == boolean.class) {
                return p.getReadMethod().getName().substring(2);
            }
            return p.getReadMethod().getName().substring(3);
        }
        return "";
    }

    /**
     * 获取千分位
     * 
     * @param s
     * @return ##,###.00
     */
    public static String getThousands(String s) {
        try {
            return THOUSANDS_TAG.format(Double.valueOf(s));
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        String s[] = { "a", "b", "c" };
        System.out.println(StringUtil.join(s, ","));
    }
}
