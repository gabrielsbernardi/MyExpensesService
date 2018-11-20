package com.br.myexpenses.cript;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptaDecriptaAES {
	private static final String IV = "0GLBMSSME1884AS0";
	private static final String KEY = "ME18WGBS20FINAPP";

    public static byte[] encrypt(String textopuro) throws Exception {
    	Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
    	SecretKeySpec key = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
    	encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    	return encripta.doFinal(textopuro.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] texto) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(texto),"UTF-8");
    }
}
