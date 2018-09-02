/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author kllp0648
 * @version $Id: AESUtils, v 0.1 2018/3/21 15:27 kllp0648 Exp $
 */
public class AESUtils {
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {

        byte[] raw = password.getBytes(Charsets.UTF_8);
        if (raw.length != 16) {
            throw new RuntimeException("Invalid key size. " + password + ", 密钥token长度不是16位");
        }
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16])); // zero IV
            byte[] finalCode = cipher.doFinal(content.getBytes(Charsets.UTF_8));
            return new String(Base64.encodeBase64(finalCode));
        } catch (Exception e) {
            throw new RuntimeException("加密参数错误", e);
        }
    }

    /**
     * 解密
     *
     * @param content 需要解密的内容
     * @param password 加密密码
     * @return
     */
    public static String decrypt(String content, String password) {
        byte[] raw = password.getBytes(Charsets.UTF_8);
        if (raw.length != 16) {
            throw new RuntimeException("Invalid key size. " + password + ", 密钥token长度不是16位");
        }
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
            byte[] toDecrypt = Base64.decodeBase64(content.getBytes());

            byte[] original = cipher.doFinal(toDecrypt);
            return new String(original, Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密参数错误", e);
        }
    }


/*    public static void main(String[] args) throws Exception{
        System.out.print(encrypt("abcdefghigklmnopqrstuvwxyz0123456789","1234567890123456"));
        System.out.print("------------------------");
       System.out.print(decrypt("8Z3dZzqn05FmiuBLowExK0CAbs4TY2GorC2dDPVlsn/tP+VuJGePqIMv1uSaVErr","1234567890123456"));
    }*/
}