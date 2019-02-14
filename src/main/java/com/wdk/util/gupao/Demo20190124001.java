package com.wdk.util.gupao;

/**
 * @Description
 * 递归算法实现 数字全排列 (深度优先算法)
 * 1,2,3
 * 1,3,2
 * 2,1,3
 * 2,3,1
 * 3,1,2
 * 3,2,1
 * ....
 *
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/1/24 10:23
 * @Since version 1.0.0
 */
public class Demo20190124001 {

    static int max = 3;

    static boolean [] flag = new boolean[max];

    static int [] data = new int[max];

    private static void dfs(int pos){

        if(pos == max){
            for(int d : data){
                System.out.print(d+",");
            }
            System.out.println();
            return;
        }


        for(int i=0; i<max; i++){
            if(flag[i] == false){
                flag[i] = true;
                data[pos] = i+1;
                dfs(pos+1);
                flag[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        dfs(0);
    }

}
