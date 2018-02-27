package com.wdk.util.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

	public static void testRead() throws IOException{
		FileInputStream fileInputStream = new FileInputStream("F:/test.txt");
		
		FileChannel fc = fileInputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		fc.read(buffer);
		
		System.out.println();
	}
	
	public static void copyFile() throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream("F:/test1.txt");
		FileChannel fc = fileOutputStream.getChannel();
		
		FileInputStream fileInputStream = new FileInputStream("F:/test.txt");
		
		FileChannel fi = fileInputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		while(true){
			if(fi.read(buffer)==-1){
				break;
			};
			buffer.flip();
			fc.write(buffer);
			buffer.clear();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		testRead();
		copyFile();
	}
}
