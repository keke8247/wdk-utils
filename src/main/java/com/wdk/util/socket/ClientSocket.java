package com.wdk.util.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	@Description
 *	socket客户端
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年5月11日 下午4:54:15
 *  @since version 1.0.0
 */
public class ClientSocket {
	private static final Logger log = LoggerFactory.getLogger(ClientSocket.class); 
	
	private String ip;
	
	private int port;
	
	private Socket  socket = null;
	
	PrintWriter out = null;
	
	BufferedReader in = null;
	
	DataInputStream getMessageStream = null;
	
	public ClientSocket(String ip,int port){
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * 创建socket连接
	 * @description
	 * @throws Exception
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	public boolean createConnection() throws Exception{
		try {
			socket = new Socket(ip, port);
			log.info("socket连接创建成功!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(null != socket){
				socket.close();
			}
			return false;
		} finally {
			
		}
	}
	
	/**
	 * 发送消息
	 * @description
	 * @param sendMessage
	 * @author wangdk
	 * @return void
	 * @throws Exception 
	 * @since  1.0.0
	*/
	public String sendMessage(String sendMessage) throws Exception{
		String answerMessage = null;
		try {
			log.info("socket = "+socket);
			
			log.info("请求报文:"+sendMessage);
			
			// 设置IO句柄
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			out.println(sendMessage);
			
			answerMessage = in.readLine();
			
			log.info("响应报文:"+answerMessage);
		} catch (Exception e) {
			log.error("发送socket消息出现异常!");
			e.printStackTrace();
			if(null != socket){
				socket.close();
			}
			throw e;
		}finally{
			shutdownConnection();
			log.info("关闭socket连接!");
		}
		return answerMessage;
	}
	
	/**
	 * 接收信息
	 * @description
	 * @return
	 * @author wangdk
	 * @return DataInputStream
	 * @throws Exception 
	 * @since  1.0.0
	*/
	public DataInputStream getMessageStream() throws Exception{
		try {
			getMessageStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			return getMessageStream;
		} catch (Exception e) {
			e.printStackTrace();
			if(null != getMessageStream){
				getMessageStream.close();
			}
			throw e;
		} finally {
			
		}
	}
	
	/**
	 * 关闭连接
	 * @description
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	public void shutdownConnection(){
		try {
			if(null != out){
				out.close();
			}
			if(null != in){
				in.close();
			}
			if(null != getMessageStream){
				getMessageStream.close();
			}
			if(null != socket){
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
