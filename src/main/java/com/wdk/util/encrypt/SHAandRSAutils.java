package com.wdk.util.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *	@Description
 *	安全工具类
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年11月17日 下午2:07:57
 *  @since version 1.0.0
 */
public class SHAandRSAutils {
	private static final Logger logger = LoggerFactory.getLogger(SHAandRSAutils.class);
	
	private static final String pubKey = "pubkey.dat";
	
	private static final String privateKey = "privatekey.dat";
	
	private static final String ENCODE = "UTF-8";
	
	/**
	 * @description
	 * 先hash数据 再做数据签名
	 * @param str 签名数据明文
	 * @return
	 * @author wangdk
	 * @return String
	 * @since  1.0.0
	*/
	public static String signData(String str){
		logger.info("待签名明文:"+str);
		String resultStr = null;
		try {
			//对待签名数据做SHA256 哈希处理
			String staySignData = DigestUtils.encodeSHA256Hex(str.getBytes(ENCODE));//生成长度64位的16进制字符串
			//获取私钥
			FileInputStream f = new FileInputStream(privateKey);  
	        ObjectInputStream b = new ObjectInputStream(f);  
	        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();  
	        
	        Signature instance = Signature.getInstance("SHA1withRSA"); //获取 签名实例
	        instance.initSign(prk); //注入签名私钥
	        
	        byte[] digest = staySignData.getBytes("UTF-8");//转换待签名数据 为 字节数组
			instance.update(digest); 
			byte[] signData = instance.sign(); //数据签名
			
			resultStr = Hex.encodeHexString(signData);//签名结果 转成String 返回
			
			f.close();
			b.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return resultStr;
	}
	
	public static String signDataTest(String str){
		logger.info("待签名明文:"+str);
		String resultStr = null;
		try {
			//对待签名数据做SHA256 哈希处理
			String staySignData = DigestUtils.encodeSHA256Hex(str.getBytes(ENCODE));//生成长度64位的16进制字符串
			//获取私钥
			FileInputStream f = new FileInputStream("privatekey.dat");  
			
			ObjectInputStream b = new ObjectInputStream(f);  
			RSAPrivateKey prk = (RSAPrivateKey) b.readObject();  
			
			Signature instance = Signature.getInstance("SHA1withRSA"); //获取 签名实例
			instance.initSign(prk); //注入签名私钥
			
			byte[] digest = staySignData.getBytes("UTF-8");//转换待签名数据 为 字节数组
			instance.update(digest); 
			byte[] signData = instance.sign(); //数据签名
			
			resultStr = Hex.encodeHexString(signData);//签名结果 转成String 返回
			
			f.close();
			b.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return resultStr;
	}
	
	/**
	 * @description
	 * 验证数字签名
	 * @param str 签名明文
	 * @param signData 签名数据
	 * @param pubKey 公钥
	 * @return
	 * @author wangdk
	 * @return boolean
	 * @throws Exception 
	 * @since  1.0.0
	*/
	public static boolean checkSign(String str,String signData){
		logger.info("签名明文:"+str);
		logger.info("签名数据:"+signData);
		
		boolean flag = false;
		try {
			//签名明文 做 hash处理 生成验签明文
			String data = DigestUtils.encodeSHA256Hex(str.getBytes(ENCODE));
			
			
			//获取公钥
			FileInputStream f = new FileInputStream(pubKey);
			ObjectInputStream b = new ObjectInputStream(f);  
	        RSAPublicKey pbk = (RSAPublicKey) b.readObject();  
	        
	        Signature instance = Signature.getInstance("SHA1withRSA");//签名实例
	        instance.initVerify(pbk);//注入公钥
	        byte[] digest = data.getBytes(ENCODE);
	        instance.update(digest);
	        flag = instance.verify(Hex.decodeHex(signData.toCharArray())); //验签
	        
	        f.close();
			b.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}  
		return flag;
	}
	
	/**
	 * @description
	 * 生成 秘钥字串
	 * @throws NoSuchAlgorithmException
	 * @author wangdk
	 * @return void
	 * @throws IOException 
	 * @since  1.0.0
	*/
	public static void createKey() throws NoSuchAlgorithmException, IOException{
		SecureRandom random = new SecureRandom();
		
		KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA",new BouncyCastleProvider());
		kg.initialize(2048, random);
		KeyPair keyPair = kg.generateKeyPair();
		PrivateKey priKey = keyPair.getPrivate();
		PublicKey pubKey = keyPair.getPublic();
		
		File file1 = new File("pubKey_1.dat");
        File file2 = new File("privateKey_1.dat");
        if (!file1.exists())
            file1.createNewFile();
        if (!file2.exists())
            file2.createNewFile();
        // 保存公钥
        FileOutputStream outputStream1 = new FileOutputStream(file1);
        ObjectOutputStream ob1 = new ObjectOutputStream(outputStream1);// 创建ObjectOutputStream对象
        ob1.writeObject(pubKey); // 将指定的对象写入 ObjectOutputStream。
        
        outputStream1.close();
        ob1.close();
        
        // 保存私钥
        FileOutputStream outputStream2 = new FileOutputStream(file2);
        ObjectOutputStream ob2 = new ObjectOutputStream(outputStream2);
        ob2.writeObject(priKey);
        
        outputStream2.close();
        ob2.close();
		
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//		createKey();
		
		String testStr = "待签名数据";
		
		String signData = signData(testStr);
		
		System.out.println(signData);
		
		String testStr1 = "待签名数据";
		
		System.out.println(checkSign(testStr1, signData));
	}
}
