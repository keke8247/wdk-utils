package com.wdk.util.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
*   罗马字符
*   I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/6 14:10
 * @Since version 1.0.0
 */
public class RomanToInt {
    private static Map romanDic = new HashMap<String,Integer>(){
        {
            put("I", 1);
            put("IV", 4);
            put("V", 5);
            put("IX", 9);
            put("X", 10);
            put("XL", 40);
            put("L", 50);
            put("XC", 90);
            put("C", 100);
            put("CD", 400);
            put("D", 500);
            put("CM", 900);
            put("M", 1000);
        }
    };

    public static int romanToInt(String roman){
        if(roman == null || "".equals(roman)){
            return 0;
        }

        int intNum = 0;

        for(int i=0;i<roman.length();){
            if(i+2<=roman.length() && romanDic.containsKey(roman.substring(i,i+2))){
                intNum += Integer.valueOf(romanDic.get(roman.substring(i,i+2)).toString());
                i += 2;
            }else{
                intNum += Integer.valueOf(romanDic.get(roman.substring(i,i+1)).toString());
                i ++;
            }
        }
        return intNum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }
}
