/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.test;

import com.github.demo.llkang.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author kll49556
 * @version $Id: PrimeNumbers, v 0.1 2018/4/24 16:04 kll49556 Exp $
 */
public class Numbers {

    /**
     * 1-upTo 之间质数的个数
     * @param upTo
     * @return
     */
    public long countPrimes(int upTo){
        return IntStream.range(1,upTo).filter(i -> isPrimes(i)).count();
    }

    /**
     * number是否是质数
     * @param number
     * @return
     */
    public boolean isPrimes(int number){
        return IntStream.range(2,number).allMatch(x -> (number % x) != 0);//全部匹配
    }

    /**
     * numbers数求和
     * @param numbers
     * @return
     */
    public BigDecimal sum(List<BigDecimal> numbers){
        return numbers.stream().reduce(BigDecimal.ZERO,(a,b) -> BigDecimalUtils.filter(a).add(BigDecimalUtils.filter(b)));
    }

    /**
     * numbers中最大值
     * @param numbers
     * @return
     */
    public int max(List<Integer> numbers){
        return numbers.stream().reduce(Integer::max).get();//Optional<Integer>
    }
}
