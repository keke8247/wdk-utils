package com.wdk.util.leetcode;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Description
 *  求字符串最长前缀
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/6 15:01
 * @Since version 1.0.0
 */
public class LongestCommonPre {

    static String pre = null;

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    public static void main(String[] args) {
        String [] pams = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(pams));
    }
}
