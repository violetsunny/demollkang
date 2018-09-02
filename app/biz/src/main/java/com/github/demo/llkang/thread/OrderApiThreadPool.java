/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.demo.llkang.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * op线程池
 *
 * @author gy22422
 * @version $Id: OrderApiThreadPool.java, v 0.1 2017年7月12日 下午8:16:19 gy22422 Exp $
 */
public class OrderApiThreadPool {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 静态内部类
     *
     * @author gy22422
     * @version $Id: OpplatformThreadPool.java, v 0.1 2017年7月12日 下午8:25:22 gy22422 Exp $
     */
    private static final class OrderApiThreadPoolHolder {
        private static final OrderApiThreadPool INSTANCE = new OrderApiThreadPool();
    }

    public static final OrderApiThreadPool getInstance() {
        return OrderApiThreadPoolHolder.INSTANCE;
    }

    /**
     * 执行线程
     *
     * @param command
     */
    public void execute(Runnable command) {
        executor.execute(command);
    }
}
