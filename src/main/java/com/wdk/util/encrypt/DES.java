package com.wdk.util.encrypt;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES 算法 加解密工具类
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 * @author liuf
 */
public class DES {

    private final static Logger logger           = LoggerFactory.getLogger(DES.class);
    private final static String ALGORITHM        = "DES";                             //算法类型
    private final static String TRANSFORMATION   = "DES";                             //算法
    private final static String ENCRYPT_PASSWORD = "LOANPLATFORM_DES";                //自定义密钥 ,长度要是8的倍数
    private static Cipher       encryptCipher;                                        //创建加密 密码器
    private static Cipher       decryptCipher;                                        //创建解密 密码器
    static {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);//创建一个密匙工厂
            DESKeySpec dks = new DESKeySpec(ENCRYPT_PASSWORD.getBytes());
            SecretKey secretKey = keyFactory.generateSecret(dks);

            encryptCipher = Cipher.getInstance(TRANSFORMATION);
            decryptCipher = Cipher.getInstance(TRANSFORMATION);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey); //初始化    
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
        } catch (Exception e) {
            logger.error("DES工具类初始化失败，", e.getStackTrace());
        }
    }

    /**将二进制转换成16进制                                                 
    * @param buf                                                            
    * @return                                                               
    */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制                                                                                          
    * @param hexStr                                                                                                  
    * @return                                                                                                        
    */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            byte[] results = encryptCipher.doFinal(content.getBytes());
            return parseByte2HexStr(results);
        } catch (Exception e) {
            logger.error("DES加密失败，", content);
            throw new RuntimeException("DES加密失败");
        }
    }

    /**
     * 加密
     * @param content
     * @param password :自定义密钥 ,长度要是8的倍数
     * @return
     */
    public static String encrypt(String content, String encrypt_password) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);//创建一个密匙工厂
            DESKeySpec dks = new DESKeySpec(encrypt_password.getBytes());
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);//使用Cipher的实例     
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
            byte[] results = cipher.doFinal(content.getBytes());
            return parseByte2HexStr(results);
        } catch (Exception e) {
            logger.error("DES加密失败，", content);
            throw new RuntimeException("DES加密失败");
        }
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        try {
            byte[] contents = parseHexStr2Byte(content);
            byte[] result = decryptCipher.doFinal(contents);
            return new String(result);
        } catch (Exception e) {
            logger.error("DES解密失败，", content);
            throw new RuntimeException("DES解密失败");
        }
    }

    /**
     * 解密
     * @param content
     * @param password :自定义密钥 ,长度要是8的倍数
     * @return
     */
    public static String decrypt(String content, String encrypt_password) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);//创建一个密匙工厂
            DESKeySpec dks = new DESKeySpec(encrypt_password.getBytes());
            SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);//使用Cipher的实例     
            cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
            byte[] contents = parseHexStr2Byte(content);
            byte[] result = cipher.doFinal(contents);
            return new String(result);
        } catch (Exception e) {
            logger.error("DES解密失败，", content);
            throw new RuntimeException("DES解密失败");
        }
    }

    /**
     * main方法  。
     * @param args
     */
    public static void main(String[] args) {
        String content = "120101198701100190";
        System.out.println("加密前：" + content);
        String encryptResultStr = DES.encrypt(content);
        System.out.println("加密后：" + encryptResultStr);
        String decryptResult = DES.decrypt(encryptResultStr);
        System.out.println("解密后：" + new String(decryptResult));
    }
}
