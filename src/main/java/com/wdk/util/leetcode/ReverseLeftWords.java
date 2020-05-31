package com.wdk.util.leetcode;

/**
 * @Description:
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 例:
 *  输入: s = "abcdefg", k = 2
 *  输出: "cdefgab"
 * @Author:wang_dk
 * @Date:2020-05-30 22:00
 * @Version: v1.0
 **/

public class ReverseLeftWords {

    public static void reverseLeftWords(String str,int k){
        if(k<1 || k>str.length() || str.length()>1000){
            System.out.println("请输入合法的参数.");
            return;
        }

        String tmp = str.substring(0,k);

        String resStr = str.substring(k,str.length())+tmp;

        System.out.println(resStr);
    }

    public static void main(String[] args) {
        reverseLeftWords("hrxiqemedq",1);
    }

}
