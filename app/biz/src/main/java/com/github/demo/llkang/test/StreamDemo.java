/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.test;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流接口（Streams）
 java.util.Stream代表着一串你可以在其上进行多种操作的元素。流操作既可以是连续的也可以是中断的。
 中断操作返回操作结果。而连续操作返回流本身，这样你就可以在该行上链式方法调用。
 流是创建在数据源上的，例如：java.util.Collection、list集合和set集合(Map不支持)。流操作既可以顺序执行也可以并行执行。
 我们首先了解下顺序的流是如何工作的。我们首先创建一个字符串链表。
 */
public class StreamDemo {
    static List<String> stringCollection = new ArrayList<>();

    static{
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
    }
    //Java8的Collections类已经被扩展了，你可以简单的调用Collection.stream()或者Collection.parallelSteam()来创建流。
    //下面部分将介绍大部分流操作。

    /**
     * Filter接受一个predicate来过滤流中的所有元素。这个操作是连续的，它可以让我们在结果上继续调用另外一个流操作forEach。
     * ForEach接受一个consumer，它被用来对过滤流中的每个元素执行操作。ForEach是一个中断操作.
     * forEach返回void,因此我们不能在ForEach后调用其他流操作。
     */
    public void Filter(){

        stringCollection
                .stream()
                .filter( (s) ->s.startsWith("a") )
                .forEach(System.out::println);
        // "aaa2" , "aaa1"
    }

    /**
     * Sorted is an intermediate operation which returns a sorted view of the stream.
     * The elements are sorted in natural order unless you pass a custom Comparator.
     */
    public void Sorted(){
        stringCollection
                .stream()
                .sorted()
                .filter( (s) -> s.startsWith("a") )
                .forEach(System.out::println);
        // "aaa1" , "aaa2"

        //Keep in mind that sorted does only create a sorted view of the stream
        //without manipulating the ordering of the backed collection.
        //The ordering of stringCollection is untouched:
        System.out.println(stringCollection); //[ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1]
    }

    /**
     * Map
     连续性操作map通过指定的Function将流中的每个元素转变为另外的对象。
     下面的示例将每个字符串转换为大写的字符串。
     此外，你也可以使用map将每个元素的类型改变为其它类型。
     转换后流的泛型类型依赖于你传入的Function的泛型类型。
     */
    public void Map(){
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted( (a, b) -> b.compareTo(a))
                .forEach(System.out::print);
        // // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
    }

    /**
     * 各种匹配操作可以用来检测某种predicate是否和流中元素相匹配。所有的这些操作是中断的并返回一个boolean结果。
     */
    public void Match(){
        boolean anyStartWithA =
                stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        System.err.println(anyStartWithA); // true

        boolean allStartWithA =
                stringCollection.stream().allMatch((s)->s.startsWith("a"));
        System.err.println(allStartWithA); // false

        boolean noneStartWithA =
                stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        System.err.println(noneStartWithA); // true
    }

    /**
     * Count
     Count是中断型操作，它返回流中的元素数量。
     */
    public void Count(){
        long startWithB =
                stringCollection
                        .stream()
                        .filter((s)->s.startsWith("b"))
                        .count();
        System.err.println(startWithB); // 3
    }

    /**
     * Reduce
     * 这个中断性操作使用指定的function对流中元素实施消减策略。此操作的返回值是一个包括所有被消减元素的Optional。
     */
    public void Reduce(){
        Optional<String> optional =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2)->s1+"#"+s2 );

        optional.ifPresent(System.out::print);
        // aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
    }


    public static void main(String[] args){
        /**相互组合**/
        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2).collect(Collectors.toList())).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("================================================");
        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);

        //实际上返回的类似是不同的
        List<Stream<String>> list2Result = list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        List<String> list2Result2 = list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());

        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
        distinct().mapToInt(num -> num * 2).
        peek(System.out::println).skip(2).limit(4).sum());

        List<String> strs = Arrays.asList("好,好,学", "习,天,天", "向,上");

        List<String[]> strArray = strs.stream().map(str -> str.split(",")).collect(Collectors.toList());

        // flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流 。
        List<String> strList = strs.stream().map(str -> str.split(",")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("strList => " + strList);
        System.out.println("================================================");

        List<List<String>> sss = Arrays.asList(Lists.newArrayList("1","2"), Lists.newArrayList("3","4"), Lists.newArrayList("5","6"));
        List<String> sss2 = sss.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("sss2 => " + sss2);
    }
}
