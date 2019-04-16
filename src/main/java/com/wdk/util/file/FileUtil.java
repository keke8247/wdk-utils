package com.wdk.util.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {
	/**
	 * @Description:
	 * 复制文件 通过 非直接缓冲区
	 * 需要先把 资源存放到JVM缓冲区中 来回拷贝
	 * @Param
	 * 		sourceFile 源文件
	 * 		targetFile 目标文件
	 * @return
	*/
	public static void copyFileByNoCache(String sourceFile,String targetFile) throws Exception {
		long start = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream(sourceFile);
		FileOutputStream fos = new FileOutputStream(targetFile);

		FileChannel fic = fis.getChannel();
		FileChannel foc = fos.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		while (fic.read(byteBuffer)!=-1){
			byteBuffer.flip();
			foc.write(byteBuffer);
			byteBuffer.clear();
		}

		fic.close();
		foc.close();
		fis.close();
		fos.close();
		long end = System.currentTimeMillis();
		System.out.println("非直接缓存复制文件执行完毕,耗时time="+(end-start));
	}

	/**
	 * @Description:
	 * 使用 直接缓冲区 拷贝文件
	 * 直接把资源放到 物理内存 不需要来回拷贝 占用内存
	 * @Param
	 * @return
	*/
	public static void copyFileByCache(String sourceFile,String targetFile) throws IOException {
		long start = System.currentTimeMillis();

		//创建管道
		FileChannel inChannel = FileChannel.open(Paths.get(sourceFile),StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get(targetFile),StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

		//创建映射
		MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
		MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());

		//直接对缓冲区操作
		byte [] dsf = new byte[inMappedBuffer.limit()];
		inMappedBuffer.get(dsf);
		outMappedBuffer.put(dsf);

		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("直接缓冲区拷贝文件执行完成,耗时time="+(end-start));
	}

	//使用 直接缓冲区 比 使用非直接缓冲区 要快很多.
	public static void main(String[] args) throws Exception {
		copyFileByNoCache("F://doc/copyFile/sourceFile.pdf","F://doc/copyFile/targetFile1.pdf");
		copyFileByCache("F://doc/copyFile/sourceFile.pdf","F://doc/copyFile/targetFile2.pdf");
	}
}
