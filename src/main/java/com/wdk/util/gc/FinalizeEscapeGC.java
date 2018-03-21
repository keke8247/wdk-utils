package com.wdk.util.gc;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 此代码演示了两点
 * 1.对象可以在被GC的时候自我拯救（成功与引用链上的任何一个对象建立关联即可）
 * 2.这种拯救自己的机会只有一次，因为一个对象的finalize()方法最多只会被系统调用一次。
 * */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	public void isAlive(){
		System.out.println("Yes,I am still alive!!");
	}
	@Override
	protected void finalize()throws Throwable{
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//因为finalize()方法的优先级很低，暂停0.5秒，以等待它
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No I am dead!!!");
		}
		//下面这段代码与上面的代码完全一致，但是这次自救却失败了。
		//证明自我拯救的机会只有一次，finalize方法只会被系统自动调用一次。
		SAVE_HOOK = null;
		System.gc();
		//因为finalize()方法的优先级很低，暂停0.5秒，以等待它
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No I am dead!!");
		}
	}
	
	public static void test() {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.163.com");// 设定smtp邮件服务器
		senderImpl.setPort(25);
		SimpleMailMessage mailMessage = new SimpleMailMessage(); 
		mailMessage.setFrom("keke8247@163.com");
		mailMessage.setSubject("标题");
		mailMessage.setText("内容。。。");
		senderImpl.setUsername("keke8247@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("wangyifan031209"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put(" mail.smtp.timeout ", " 25000 ");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		mailMessage.setTo("1531451174@qq.com");
//		senderImpl.send(mailMessage);
		System.out.println("send OK by lkmgydx");
	}
}
