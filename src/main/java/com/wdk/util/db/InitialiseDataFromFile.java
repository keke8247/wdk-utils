package com.wdk.util.db;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description
 * 把格式化后的文件内容 初始化到数据库
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/4/15 10:05
 * @Since version 1.0.0
 */
public class InitialiseDataFromFile {

    private static Statement statement;

    private static Statement getStatement(){
        if(null == statement){
            try {
                statement = DBHelper.conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static int initialiseData(String filePath,String sqlStr) throws IOException {
        File file = new File(filePath);

        //判断文件是否存在
        if(file.exists()){
            BufferedReader bf = null;
            try {
                bf = new BufferedReader(new FileReader(file));
                String tmpString = null;
                //整行读取
                while ((tmpString = bf.readLine()) != null){
                   String strArr[] = tmpString.split(",");

                   String sql = sqlStr ;
                   for(String str : strArr){
                       sql = sql.replaceFirst("\\?",str);
                   }
                   getStatement().execute(sql);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bf.close();
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        initialiseData("F://doc/initData.txt","insert into test_init (username,password) values ('?','?')");
    }
}
