package com.github.demo.llkang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ly.sof.utils.common.DateUtil;

/**
 *
 *
 * @author kllp0648
 * @version $Id: DateFormat.java, v 0.1 2017年2月8日 下午7:08:43 kllp0648 Exp $
 */
public class DateFormat {
    /**  */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *
     * @param orgi
     * @param dest
     * @return
     * @throws ParseException
     */
    public static Date assmeTime(String orgi, String dest) throws ParseException {
        Date orgiDate = stringToDate(orgi, DEFAULT_DATE_FORMAT);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date destDate = simpleDateFormat.parse(dest);
        Calendar orgiCal = Calendar.getInstance();
        orgiCal.setTime(orgiDate);
        Calendar destCal = Calendar.getInstance();
        destCal.setTime(destDate);
        orgiCal.set(Calendar.HOUR_OF_DAY, destCal.get(Calendar.HOUR_OF_DAY));
        orgiCal.set(Calendar.MINUTE, destCal.get(Calendar.MINUTE));
        return orgiCal.getTime();
    }

    /**
     * 字符串转日期格式。
     *
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str, String format) throws ParseException {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = (Date) sdf.parse(str);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        return "";

    }

    /**
     * 默认日期转字符串
     *
     * @param date Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String zhDate(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 默认日期转字符串
     *
     * @param date Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date zhDate(String date) {
        if (null == date) {
            return null;
        }
        try {
            return stringToDate(date, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 默认日期转字符串
     *
     * @param date yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static Date noTimeDate(String date) {
        if (null == date) {
            return null;
        }
        try {
            return stringToDate(date, "yyyy-MM-dd");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 生成供占座接口使用的时间格式
     *
     * @param date 乘客选择日期(yyyy-MM-dd)
     * @param time 航班起飞或到达时间(HH:mm)
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String genBookPnrTime(String date, String time) {
        return date + " " + time + ":00";
    }

    /**
     * 生成订单使用的出发或到达日期(PlanBeginDate,setPlanEndDate)
     *
     * @param date 乘客选择日期(yyyy-MM-dd)
     * @param time 航班起飞或到达时间(HH:mm)
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date genPlanDate(String date, String time) {
        try {
            return DateUtil.parseDateNoTime(date + " " + time + ":00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 生成带星期的日期，航班订单信息确认页使用
     *
     * @param date 乘客所选日期String(yyyy-MM-dd)
     * @return yyyy-MM-dd （EEEE）
     */
    public static String convert(String date) {
        if (null == date) {
            return "";
        }
        try {
            return DateUtil.format(DateUtil.parseDateNoTime(date, "yyyy-MM-dd"), "yyyy-MM-dd （EEEE）");
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 生成FlightOrderVO使用的出发或到达时间
     *
     * @param time 时间(HH:mm)
     * @return 时间(HH:mm)
     */
    public static Date genOrderTime(String time) {
        try {
            return DateUtil.parseDateNoTime(time, "HH:mm");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else //不同年
        {
            return day2 - day1;
        }
    }

    /**
     * 时间是否在给定时间内
     *
     * @param date
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isInDate(Date date, Date startDate, Date endDate) {
        if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 前一天
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 默认日期转字符串
     *
     * @param date yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String noTimeDate(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }
    
    /**
     * 获取去除时间的日期
     * 
     * @param date
     * @return
     */
    public static Date getNoTimeDate(Date date){
        if(null == date){
            return null;
        }
        return noTimeDate(noTimeDate(date));
    }
    
    /**
     * long时间转换日期
     * 
     * @param time
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDate(long time,String format) throws ParseException{
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String str = sdf.format(time);
            date = (Date) sdf.parse(str);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
    
    /**
     * long时间转string
     * 
     * @param times
     * @param format
     * @return
     */
    public static String date2Str(long time, String format) {
        SimpleDateFormat sdateFormat =new SimpleDateFormat(format);
        String strdate=sdateFormat.format(time);
        return strdate;
    }
}
