package com.wdk.util.rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Password_Test {
    public static void main(String[] args) {
        try {
            new Password_Test();
            Encryption_RSA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public Password_Test() throws Exception {// 构造方法 创建公钥和私钥
    	SecureRandom random = new SecureRandom();
    	
    	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");// 生成实现RSA算法的KeyPairGenerator对象。
        kpg.initialize(2048,random);// 初始化确定密钥的大小
        KeyPair kp = kpg.genKeyPair();// 生成密钥对
        PublicKey pbkey = kp.getPublic();// 创建公钥
        PrivateKey prkey = kp.getPrivate();// 创建私钥
        File file1 = new File("pubKey.dat");
        File file2 = new File("privateKey.dat");
        if (!file1.exists())
            file1.createNewFile();
        if (!file2.exists())
            file2.createNewFile();
        // 保存公钥
        FileOutputStream outputStream1 = new FileOutputStream(file1);
        ObjectOutputStream ob1 = new ObjectOutputStream(outputStream1);// 创建ObjectOutputStream对象
        ob1.writeObject(pbkey); // 将指定的对象写入 ObjectOutputStream。
        outputStream1.close();
        ob1.close();
        
        // 保存私钥
        FileOutputStream outputStream2 = new FileOutputStream(file2);
        ObjectOutputStream ob2 = new ObjectOutputStream(outputStream2);
        ob2.writeObject(prkey);
        outputStream2.close();
        ob2.close();
    }
 
    public static void Encryption_RSA() throws Exception {
        System.out.println("根据公钥生成密文：\n");
        String string = "I am a student";
        // 获取公钥及参数e,n
        FileInputStream f_in = new FileInputStream("Skey_RSA_pub.dat");
        ObjectInputStream o_in = new ObjectInputStream(f_in);
        RSAPublicKey pbk = (RSAPublicKey) o_in.readObject();
        BigInteger e = pbk.getPublicExponent();// 返回此公钥的指数
        BigInteger n = pbk.getModulus();// 返回此公钥的模
        System.out.println("公钥的指数 e= " + e);
        System.out.println("公钥的模 n= " + n);
        // 明文 bit
        byte bt[] = string.getBytes("UTF8");
        BigInteger bit = new BigInteger(bt);
        // 计算密文c,打印
        BigInteger mi = bit.modPow(e, n);// 生成密文
        System.out.println("生成密文为： " + mi + "\n");// 打印密文
        // 保存密文
        String save = mi.toString();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Enc_RSA.dat")));
        out.write(save, 0, save.length());
        out.close();
        Decrypt_RSA();
    }
 
    public static void Decrypt_RSA() throws Exception {
        System.out.println("根据私钥破解密文：\n");
        // 读取密文
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("Enc_RSA.dat")));
        String ctext = in.readLine();
        BigInteger mi = new BigInteger(ctext);
        // 读取私钥
        FileInputStream f = new FileInputStream("Skey_RSA_priv.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();// 返回此私钥的指数
        BigInteger n = prk.getModulus();// 返回此私钥的模
        System.out.println("私钥的指数 d= " + d);
        System.out.println("私钥的模 n= " + n);
        BigInteger jie = mi.modPow(d, n);// 进行解密操作
        System.out.println("m= " + jie); // 显示解密结果
        byte[] mt = jie.toByteArray();
        System.out.println("解密后的文本内容为： ");
        for (int i = 0; i < mt.length; i++) {
            System.out.print((char) mt[i]);
        }
    }
}
