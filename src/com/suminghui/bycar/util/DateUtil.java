package com.suminghui.bycar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期工具类
 * @author andy.zhang
 *
 */
public class DateUtil {

    public static String getPastMonth() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.MONTH, -1);
        return df.format(nowDate.getTime());
    }

    public static String getPastWeek() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.DAY_OF_WEEK, -7);
        return df.format(nowDate.getTime());
    }

    public static String getPastYear() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.YEAR, -1);
        return df.format(nowDate.getTime());
    }

    public static String getNextDate() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.DATE, +1);
        return df.format(nowDate.getTime());
    }
    
    public static String getPastDate() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.DATE, -1);
        return df.format(nowDate.getTime());
    }
    
    public static String getTodyDate() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowDate = Calendar.getInstance();
        return df.format(nowDate.getTime());
    }
}
