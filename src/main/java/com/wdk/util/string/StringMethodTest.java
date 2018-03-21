package com.wdk.util.string;

import java.nio.charset.Charset;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/21 9:51
 * @Since version 1.0.0
 */
public class StringMethodTest {

    public static void testNewStringFromChar(){
        char [] c = {'a','c','r','f','1','t','x'};

        String str = new String(c,2,3);

        System.out.println(str);
    }

    public static void testNewStringFromIntArray(){
        int [] ints = {-1,1,3,4,1,23,-12,44,12,-2};

        String str = new String(ints,2,4);

        System.out.println(str);
    }

    public static void testCodePointAt(){
        String str = "2342sgrdsgda2112";

        int i = str.codePointAt(2);

        System.out.println(i);
    }

    public static void testGetChars(){
        byte[] b = new byte[10];

        String str = "dkihgaeirhxxcx";

        str.getBytes(2,5,b,0);

        System.out.println(new String(b));

        System.out.println(new String(str.getBytes(Charset.forName("UTF-8"))));
    }

    public static void testStartWith(){
        String str1 = "1231dwrfewrw";

        String str2 = "dwr";

        System.out.println(str1.startsWith(str2,4));
    }

    public static void testCharTOInt(){
        char x = 'x';

        System.out.println((int)x);

    }

    public static void testIndexOf(){
        String str = "kkskefjweew";

        String hex = "0x010000";

        System.out.println(Integer.parseInt(hex.substring(2),16)); //16进制转10进制

        System.out.println(str.indexOf(107));
    }

    public static void testReplace(){
        String str = "this is my book";

        System.out.println(str.replace("my","his"));
    }

    public static void testSplit(){
        String str = "boo:and:foo";

        //limit 参数控制模式应用的次数，因此影响所得数组的长度。如果该限制 n 大于 0，则模式将被最多应用 n - 1 次，
        // 数组的长度将不会大于 n，而且数组的最后一项将包含所有超出最后匹配的定界符的输入。
        // 如果 n 为非正，那么模式将被应用尽可能多的次数，而且数组可以是任何长度。
        // 如果 n 为 0，那么模式将被应用尽可能多的次数，数组可以是任何长度，并且结尾空字符串将被丢弃。

        System.out.print("str.split(\":\",2):");
        for (String str1:str.split(":",2)) {
            System.out.printf("%s ",str1);
        }
        System.out.println();

        System.out.print("str.split(\":\",-2):");
        for (String str1:str.split(":",-2)) {
            System.out.printf("%s ",str1);
        }
        System.out.println();

        System.out.print("str.split(\":\",0):");
        for (String str1:str.split(":",0)) {
            System.out.printf("%s ",str1);
        }
        System.out.println();
    }

    public static void testFormat(){

        StringFormatTest.testOne();
        StringFormatTest.testTwo();

    }


    public static void main(String[] args) {
        testFormat();
    }

}
