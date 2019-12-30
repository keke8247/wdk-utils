package com.wdk.util.leetcode;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Description
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/6 16:45
 * @Since version 1.0.0
 */
public class BracketsIsValid {
    private static Map bracketsDic = new HashMap<Character,Character>(){
        {
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }
    };

    public static boolean isValid(String brackets){
        if(brackets.length()%2 !=0 ){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();

        //结题思路 如果是闭括号  去栈顶弹出一个 看是否匹配  不匹配认为无效
        for(int i=0;i<brackets.length();i++){

            if(bracketsDic.containsKey(brackets.charAt(i))){ //是闭括号
                Character topElement = stack.isEmpty()?'#':stack.pop();
                if(!topElement.equals(bracketsDic.get(brackets.charAt(i)))){
                    return false;
                }
            }else{
                stack.push(brackets.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("([()]({[]}))))"));
    }
}
