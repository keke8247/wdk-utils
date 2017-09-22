package com.wdk.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class FileUtil {
	private static final Logger log = Logger.getLogger("FileUtil");
	
	/**
	 * 以行为单位读取文件，返回字符串
	 * @param filePath 文件路径
	 */
	public static String readLinesToStr(String filePath) {
		
		File file = new File(filePath);
		String result = "";
		if (file.exists()) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	            	String str[] = tempString.split(",");
	            	
	            	DBHelper.conn.createStatement().
	            	execute("insert into sys_bank (bank_name,bank_code) values ('"+str[0]+"',"+str[1]+")");
	            }
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                }
	            }
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(readLinesToStr("G:\\bank_code.txt"));
	}
}
