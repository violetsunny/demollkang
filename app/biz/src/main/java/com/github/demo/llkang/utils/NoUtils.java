package com.github.demo.llkang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 编号生成器
 * 
 * @author gy22422
 * @version $Id: OrderNoUtils.java, v 0.1 2016年7月12日 下午5:28:40 gy22422 Exp $
 */
public class NoUtils {

    //    protected final static Logger logger        = LoggerFactory.getLogger(getClass());

    private NoUtils() {
        super();
    }

    private static Map<String, Cache> cacheMap   = new HashMap<String, Cache>();//构造map存储两秒内的订单编号

    private static long               CACHE_TIME = 2000;                        //缓存时间，2000毫秒

    /**
     * 创建订单编号
     * 
     * @return
     */
    private static String createNo(String prefix) {
        String orderNo = "";
        try {
            //获取12时间戳
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = format.format(new Date());

            //四位随机数
            String fourRandomNum = "";
            int num = (int) (Math.random() * 1000);
            if (num < 10) {
                fourRandomNum = "00" + num;
            } else if (num >= 10 && num < 100) {
                fourRandomNum = "0" + num;
            } else {
                fourRandomNum = String.valueOf(num);
            }

            orderNo = prefix + dateStr + fourRandomNum;
        } catch (Exception e) {
            return "";
        }
        return orderNo;
    }

    /**
     * 获取最新的订单编号
     * 
     * @return
     */
    public static String getNo(String prefix) {
        clearTimeOutCache();
        String orderNo = createNo(prefix);
        while (isExistOrderNo(orderNo)) {
            orderNo = createNo(prefix);
        }
        putCacheInfo(orderNo);
        return orderNo;
    }

    /**
     * 将订单编号缓存
     * 
     * @param key
     */
    private static void putCacheInfo(String key) {
        Cache cache = new Cache(key, System.currentTimeMillis());
        cacheMap.put(key, cache);
    }

    /**
     * 判断缓存中是否存在订单编号
     * 
     * @param orderNo
     * @return
     */
    private static boolean isExistOrderNo(String orderNo) {
        if (cacheMap.isEmpty()) {
            return false;
        }
        if (cacheMap.containsKey(orderNo)) {
            return true;
        }
        return false;
    }

    /**
     * 清除两秒以外的订单编号
     */
    private synchronized static void clearTimeOutCache() {
        if (cacheMap.isEmpty()) {

        } else {
            Iterator<String> it = cacheMap.keySet().iterator();
            while (it.hasNext()) {
                String keyStr = it.next();
                Cache cache = cacheMap.get(keyStr);
                long dateTime = cache.getDateTime();
                long now = System.currentTimeMillis();
                if ((now - dateTime) > CACHE_TIME) {
                    it.remove();
                }
            }
        }

    }

}
