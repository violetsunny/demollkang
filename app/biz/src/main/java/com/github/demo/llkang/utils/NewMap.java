/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kllp0648
 * @version $Id: NewMap, v 0.1 2018/3/8 17:02 kllp0648 Exp $
 */
public class NewMap {

    public static void main(String[] arg){

        Map<Integer, String> map = new HashMap<>();
        for(int i=0; i<10; i++){
            //putIfAbsent使得我们不用写是否为null值的检测语句；
            map.putIfAbsent(i, "val_"+i);
        }

        //-------1------maps不支持流。然而现在maps包括了许多新的非常有用的方法用于执行通用任务:
        //forEach使用consumer来对map中的每个元素进行操作,执行通用任务。
        map.forEach( (key,value) -> System.out.println(key +" : "+value) );

        //----------2-------------------使用功能性函数在map里执行代码：
        //将key为3对应的值(val_3)改为  "val_3"+3*10 = val_330
        map.computeIfPresent(3, (num, val) -> val + num*10);
        System.out.println(map.get(3)); //val_330

        //将key为9对应的值(val_9)改为  null
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        //将key为23对应的值(null)改为  "val_23"
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.put(3, null);
        //如果key为3对应的值改为null, 将其值改为"bam"
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));// bam

        //---------3--------------删除给定键所对应的元素。删除操作还需要满足给定的值需要和map中的值相等：
        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null


        //----------4--------其他一些帮助性方法：
        map.getOrDefault(44, "404 not found");

        //---------------5----------------合并map中的实体
        //此时map.get(9)=null
        map.merge(9, "val999", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val999
        System.err.println(map.get(9));

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val999concat
        System.err.println(map.get(9));

    }
}
