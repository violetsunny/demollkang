package com.github.demo.llkang.utils;

public class DateUtils {

    /**
     *  秒转换成具体时间
     * @param time
     * @return
     */
    public static String secToTime(Long time) {
        if (time == null || Long.valueOf(0L).equals(time)) {
            return "00分钟00秒";
        }
        String timeStr = null;
        Long hour = 0L;
        Long minute = 0L;
        Long second = 0L;
        if (time <= 0)
            return "00分钟00秒";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    /**
     * 格式补0
     * 
     * @param i
     * @return
     */
    private static String unitFormat(Long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
