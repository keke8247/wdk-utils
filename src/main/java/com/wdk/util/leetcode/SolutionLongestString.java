package com.wdk.util.leetcode;

/**
 * @Description
 * 寻找一个字符串中 最长的不重复字符串
 * 例如:abcabcbb  --> abc
 *      pwwkew -->wke
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/2/28 10:06
 * @Since version 1.0.0
 */
public class SolutionLongestString {
    public static  String lengthOfLongestSubstring(String s) {
        char [] strs = s.toCharArray();

        String str = "";
        String str2 = "";

        for (int i = 0; i < strs.length; i++) {
            String c = String.valueOf(strs[i]);
            if (str.contains(c)){
                if(str.length() > str2.length()){
                    str2 = str;
                }
                str = str.substring(str.indexOf(c)+1,str.length());
                str += c;
            }else{
                str += c;
            }
        }
        return str2.length()>str.length()?str2:str;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
