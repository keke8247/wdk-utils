package com.wdk.util.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 *
 * 输入: "abcabcbb"
   输出: 3
   解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/26 15:52
 * @Since version 1.0.0
 */
public class SolutionLengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s){
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcsldfaxv"));
    }
}
