package com.wdk.util.leetcode;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/6 11:56
 * @Since version 1.0.0
 */
public class Palindrome {

    /**
     * @Description:
     * 判断int型数值x 是否是回文数(正读倒读都一样的数字)
     * 先反转数字 再比较是否相等.
     * @Param
     * @return
    */
    public static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        int tmp = x;
        //method1
        int rev = 0;
        while (x > 0){
            int pop = x%10;
            x /= 10;

            rev = rev*10 + pop;
        }
        if(tmp == rev){
            return true;
        }
        return false;
    }

    public static boolean isPalindrome2(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber/10;
    }


    public static void main(String[] args) {
        System.out.println(isPalindrome2(121));
    }
}
