/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import com.github.demo.llkang.vo.FileVO;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内建的功能性接口
 *
 * Java8的API也包含了很多新的功能性接口简化你的开发。
 * 一些新的接口是来自非常出名的Google Guava库。
 */
public class InternalFunctionalInterface {

    /**
     * 断言接口（Predicates）
     * Predicates是只拥有一个参数的Boolean型功能的接口。这个接口拥有多个默认方法用于构成predicates复杂的逻辑术语。
     */
    public void Predicates(){

        Predicate<String> predicates = (str) -> str.length()>0;
        boolean b1 = predicates.test("foo"); //true
        System.err.println(b1);
        b1 = predicates.negate().test("foo"); //false ,取反
        System.err.println(b1);

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.err.println(isEmpty.test("sfasdfsa")); //false
        System.err.println(isNotEmpty.test("sfasdfsa")); //true

    }

    /**
     * 功能接口（Functions）
     * Functions接受一个参数并产生一个结果。默认方法能够用于将多个函数链接在一起。
     */
    public void Function(){
        Function<String, Integer> toInteger = Integer::valueOf;//(str) -> Integer.valueOf(str);
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.err.println(backToString.apply("123")); // "123"
    }

    /**
     * 供应接口（Suppliers）
     * Suppliers对于给定的泛型类型产生一个实例。不同于Functions，Suppliers不需要任何参数。
     */
    public void Supplier(){
        Supplier<FileVO> supplier = FileVO::new;
        FileVO p =  supplier.get();
        System.err.println(p);
    }

    /**
     * 消费接口（Consumers）
     Consumers代表在只有一个输入参数时操作被如何执行。
     */
    public void Consumers(){
        Consumer<FileVO> greeter = (p) -> System.err.println("Hello, "+p.getFileName());
        greeter.accept(new FileVO());
    }

    /**
     * 比较接口（Comparators）
     Comparators在老版本中就已经被熟知。Java8向该接口中添加了多种默认方法。
     */
    public void Comparator(){
        Comparator<FileVO> comparator = (p1, p2) -> p1.getFileName().compareTo(p2.getFileName());

        FileVO p1 = new FileVO();
        FileVO p2 = new FileVO();

        System.err.println(comparator.compare(p1, p2)); // 9>0       why is 9????????????????????
        System.err.println(comparator.reversed().compare(p1, p2));// -9<0
    }

    /**
     * 选项接口（Optionals）
     Optionals并不是功能性接口，反而它是一种特殊的工具用来阻止NullPointerException。
     我们首先快速的浏览Optionals是如何工作的，因为它在下一章节是十分重要的概念。
     Optional是一种可以包含null和non-null值的简单容器。考虑到方法可以返回non-null结果，偶尔也可能任何都不返回。
     在Java8中，你可以返回Optional而不是返回null。
     */
    public void Optionals(){
        Optional<String> optional = Optional.of("bam"); //不能为空

        System.err.println(optional.isPresent()); // true
        System.err.println(optional.get()); // "bam"
        System.err.println(optional.orElse("fallback"));

        optional.ifPresent( (s) -> System.err.println(s+"----------"));

    }
}
