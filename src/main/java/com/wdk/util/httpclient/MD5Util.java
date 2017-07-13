package com.wdk.util.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	/** 资源属性 */
	public static String appKey = "39v17cvcoo4tql1j";
	public static String clientID = "8515585492913365";
	
//	static {
//		properties = new Properties();
//		try {
//			// 读取配置文件
//			properties.load(MD5Util.class.getClassLoader().getResourceAsStream("tokenparam.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 方法描述:将字符串MD5加码 生成32位md5码
	 * 
	 * @param inStr
	 * @return
	 */
	public static String md5(String inStr) {
		try {
			return DigestUtils.md5Hex(inStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误");
		}
	}

	/**
	 * 方法描述:签名字符串
	 * 
	 * @param params
	 *            需要签名的参数
	 * @param appSecret
	 *            签名密钥
	 * @return
	 */
	public static String sign(Map<String, Object> params, String appSecret) {
		StringBuilder valueSb = new StringBuilder();
		params.put("appSecret", appSecret);
		// 将参数以参数名的字典升序排序
		Map<String, Object> sortParams = new TreeMap<String, Object>(params);
		Set<Entry<String, Object>> entrys = sortParams.entrySet();
		// 遍历排序的字典,并拼接value1+value2......格式
		for (Entry<String, Object> entry : entrys) {
			valueSb.append(entry.getValue());
		}
		params.remove("appSecret");
		return md5(valueSb.toString());
	}

	

	/**
	 * map按key字典升序排序后再返回字符串
	 * @param params	
	 * @param isSort 是否要字典排序
	 * @return
	 */
	public static String mapSort(Map<String, Object> params){
		Collection<String> keyset = params.keySet();
		List<String> list = new ArrayList<String>(keyset);
		
		// 对key键值按字典升序排序
		Collections.sort(list);
		
		StringBuilder signStr = new StringBuilder();
		// 迭代list拼装签名sign
		for (int i = 0; i < list.size(); i++) {
			signStr.append(list.get(i)).append("=").append(params.get(list.get(i)));
			if (i != list.size() - 1) {
				signStr.append("&");
			} 
		}
		return signStr.toString();
	}
	
	/**
	 * 生成token
	 * @param params
	 * @return
	 */
	public static String createToken(String roundStr){
		
		String tokenStr = "client_id="+clientID+"&round_str="+roundStr+"&app_key="+appKey;
		
		return md5(tokenStr);
	}
	
	/**
	 * 数据加密
	 * @param params
	 * @return
	 */
	public static String md5SignStr(Map<String, Object> params){
		return md5(mapSort(params));
	}
	
	/**
	 * @description
	 * 生成定长随机数
	 * @param Len 随机数长度
	 * @return
	 * @return String
	 * @since  1.0.0
	*/
	public static String getRandomString(int Len) {
        
        String[] baseString={"0","1","2","3",
                "4","5","6","7","8","9",
                "a","b","c","d","e",
                "f","g","h","i","j",
                "k","l","m","n","o",
                "p","q","r","s","t",
                "u","v","w","x","y",
                "z","A","B","C","D",
                "E","F","G","H","I",
                "J","K","L","M","N",
                "O","P","Q","R","S",
                "T","U","V","W","X","Y","Z"};
        Random random = new Random();
        int length=baseString.length;
        String randomString="";
        for(int i=0;i<length;i++){
            randomString+=baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr="";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length()-1));
        }
        return resultStr;
    }
	
	public static void main(String[] args) {
		System.out.println(createToken(getRandomString(10)));
	}
}
