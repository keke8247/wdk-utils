package com.wdk.util.leetcode;

/**
 * @Description
 *             最长回文字符串,
 *                  回文字符串 是从左右读都一样的字符串
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/5 15:43
 * @Since version 1.0.0
 */
public class LongestPalindrome {
    private static int nums = 0;

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        nums++;
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdefg"));
        System.out.println(nums);
    }
}
