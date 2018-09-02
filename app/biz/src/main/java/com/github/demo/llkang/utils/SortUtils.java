/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 排序工具
 *
 * @author hw24221
 * @version $Id: SortUtils, v 0.1 2017/2/27 14:35 hw24221 Exp $
 */
public class SortUtils {
    /**
     * 对城市列表根据搜索热度排序
     *
     * @param cityList
     * @return
     */
    public static List<String> insertSort(List<String> cityList){
        if (cityList == null || cityList.size() == 0){
            return null;
        }
        List<String> newCityList = new LinkedList<>(cityList);
        String cityDescribe0 = "";
        String cityDescribe1 = "";
        for (int i = 1; i < cityList.size(); i ++){
            for (int j = i; j > 0; j --){
                cityDescribe0 = newCityList.get(j-1);
                cityDescribe1 = newCityList.get(j);
                if (Long.parseLong(cityDescribe0.split(",")[2]) < Long.parseLong(cityDescribe1.split(",")[2])){
                    newCityList.remove(j);
                    newCityList.add(j-1, cityDescribe1);
                } else {
                    break;
                }

            }
        }

        return newCityList;
    }

}
