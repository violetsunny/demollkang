/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.annotation.weblimit;

import com.github.demo.llkang.utils.ScriptUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.Collections;

/**
 * @author kll49556
 * @version $Id: RedisLimit, v 0.1 2018/7/24 16:01 kll49556 Exp $
 */
@Component
public class RedisLimit {
    private JedisCommands jedis;
    private int limit = 200;
    private static final int FAIL_CODE = 0;
    /**
     * lua script
     */
    private String script;
    private RedisLimit(Builder builder) {
        this.limit = builder.limit ;
        this.jedis = builder.jedis ;
        buildScript();
    }
    /**
     * limit traffic
     * @return if true
     */
    public boolean limit() {
        String key = String.valueOf(System.currentTimeMillis() / 1000);
        Object result = null;
        if (jedis instanceof Jedis) {
            result = ((Jedis) this.jedis).eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
        } else if (jedis instanceof JedisCluster) {
            result = ((JedisCluster) this.jedis).eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
        } else {
            //throw new RuntimeException("instance is error") ;
            return false;
        }
        if (FAIL_CODE != (Long) result) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * read lua script
     */
    private void buildScript() {
        script = ScriptUtil.getScript("lua/limit.lua");
    }
    /**
     *  the builder
     * @param <T>
     */
    public static class Builder<T extends JedisCommands>{
        private T jedis = null ;
        private int limit = 200;
        public Builder(T jedis){
            this.jedis = jedis ;
        }
        public Builder limit(int limit){
            this.limit = limit ;
            return this;
        }
        public RedisLimit build(){
            return new RedisLimit(this) ;
        }
    }
}
