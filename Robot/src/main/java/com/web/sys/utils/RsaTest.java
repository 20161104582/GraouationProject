package com.web.sys.utils;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class RsaTest {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, FileNotFoundException {
		// 读取公钥私钥
		RSAPublicKey publicKey = RSAUtils.getPublicKeyString("D:\\WORK\\2020\\code\\Robot\\src\\main\\java\\com\\web\\sys\\utils\\gjzwfw.gov.cn.crt");
		RSAPrivateKey privateKey = RSAUtils.getRSAPrivateKey("D:\\tmp\\gjzwfw.gov.cn.pfx", "5LoveChina!");
		String str = "徐仁健|512925196806094495";
		System.out.println("明文：" + str);
		
		System.out.println("\n公钥加密——私钥解密");
		String encodedData = RSAUtils.publicEncrypt(str, publicKey); // 传入明文和公钥加密,得到密文
		System.out.println("密文：" + encodedData);
		String appkey = "57bf1661-3aad-4786-89a2-e187b38966ea";
		String decodedData = RSAUtils.privateDecrypt(encodedData, privateKey); // 传入密文和私钥,得到明文
		System.out.println("解密后文字: " + decodedData);
//
//		System.out.println("\n私钥加密——公钥解密");
//
//		encodedData = RSAUtils.privateEncrypt(str, privateKey);// 传入明文和私钥加密,得到密文
//		System.out.println("密文：" + encodedData);
//		decodedData = RSAUtils.publicDecrypt(encodedData, publicKey); // 传入密文和私钥,得到明文
//		System.out.println("解密后文字: " + decodedData);
	}

}
