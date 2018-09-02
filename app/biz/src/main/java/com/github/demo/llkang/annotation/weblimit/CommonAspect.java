/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.annotation.weblimit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author kll49556
 * @version $Id: CommonAspect, v 0.1 2018/7/24 16:09 kll49556 Exp $
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CommonAspect {
    private static Logger logger = LoggerFactory.getLogger(CommonAspect.class);
    @Autowired
    private RedisLimit redisLimit ;
    @Pointcut("@annotation(com.github.demo.llkang.annotation.weblimit.CommonLimit)")
    private void check(){}
    @Before("check()")
    public void before(JoinPoint joinPoint) throws Exception {
        if (redisLimit == null) {
            throw new NullPointerException("redisLimit is null");
        }
        boolean limit = redisLimit.limit();
        if (!limit) {
            logger.warn("request has bean limit");
            throw new RuntimeException("request has bean limit") ;
        }
    }
}
