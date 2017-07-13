package com.wdk.util.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SendMail {
	
	public static void testSendMail(){
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.163.com");// 设定smtp邮件服务器
		senderImpl.setPort(25);
		SimpleMailMessage mailMessage = new SimpleMailMessage(); 
		mailMessage.setFrom("keke8247@163.com");
		mailMessage.setSubject("标题");
		mailMessage.setText("内容。。。");
		senderImpl.setUsername("keke8247@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("woshimima"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put(" mail.smtp.timeout ", " 25000 ");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		mailMessage.setTo("1531451174@qq.com");
//		senderImpl.send(mailMessage);
		System.out.println("send OK by lkmgydx");
	}
	
	
	public static void main(String[] args) {
		
	}
}
