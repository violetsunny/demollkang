/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.annotation.weblimit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kll49556
 * @version $Id: WebIntercept, v 0.1 2018/7/24 15:43 kll49556 Exp $
 */
@Component
public class WebIntercept extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(WebIntercept.class);
    @Resource
    private RedisLimit redisLimit;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**");
    }
    private class CustomInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws Exception {
            if (redisLimit == null) {
                throw new NullPointerException("redisLimit is null");
            }
            if (handler instanceof HandlerMethod) {
                HandlerMethod method = (HandlerMethod) handler;
                ControllerLimit annotation = method.getMethodAnnotation(ControllerLimit.class);
                if (annotation == null) {
                    //skip
                    return true;
                }
                boolean limit = redisLimit.limit();
                if (!limit) {
                    logger.warn("request has bean limit");
                    response.sendError(500, "request limit");
                    return false;
                }
            }
            return true;
        }
    }
}
