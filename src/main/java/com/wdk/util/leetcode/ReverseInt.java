package com.wdk.util.leetcode;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/5 17:23
 * @Since version 1.0.0
 */
public class ReverseInt {
    public static int reverse(int x){
        int rev = 0;
        while (x != 0){
            int pop = x%10;
            x /= 10; //每次弹出一位

            if(rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if(rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            rev = rev*10+pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(4/-3);
        System.out.println(reverse(-123));
    }
}
