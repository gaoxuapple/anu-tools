package com.anu.tools.encription.des;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Describe: DES算法加解密工具类
 * Author: anu
 * Mail: anhui_gaoxu@126.com
 * Phone: 18355150480
 * Date: 2019年05月29日 15:02
 * Copyright: © 2019.Anu., Ltd. All rights reserved.
 */
public class DesEncryptAndDecodeUtil {

    private static final String DES = "DES";
    private static final String KEY = "!&^@I%LOVE%YOU@^&!";


    public static void main(String[] args) throws Exception {
        int count = 100000;
        String data = null;
        long startTime = 0L;
        long endTime = 0L;

        System.out.println(1);
        System.out.println(encode("root"));
        System.out.println(2);
        System.out.println(encode("123456"));
        System.out.println(3);
        System.out.println(encode("jdbc:mysql:tcp://127.0.0.1:3306/test?charset-encoding=UTF-8"));

        System.out.println(4);
        System.out.println(decode("XafBIxea9X4="));
        System.out.println(5);
        System.out.println(decode("DUoa6t69/Ko="));
        System.out.println(6);
        System.out.println(decode("u6prW3LNaRM27PfjXp9sUh5/udr3ECFJqLWeRf8u5bn+yqs0pfUsp73ZLwetIJQK6EONkF3X1ef3UWQlyIdY1g=="));
        System.out.println(7);
        data = "jdbc:mysql:tcp://127.0.0.1:3306/test?charset-encoding=UTF-8";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            encode(data);
        }
        endTime = System.currentTimeMillis();
        System.out.println("加密" + count + "次需要" + (endTime - startTime) + "毫秒。");

        data = "u6prW3LNaRM27PfjXp9sUh5/udr3ECFJqLWeRf8u5bn+yqs0pfUsp73ZLwetIJQK6EONkF3X1ef3UWQlyIdY1g==";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            decode(data);
        }
        endTime = System.currentTimeMillis();
        System.out.println("解密" + count + "次需要" + (endTime - startTime) + "毫秒。");
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        return encrypt(data.trim(), KEY);
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        return decrypt(data.trim(), KEY);
    }


    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[].
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程.
     *
     * @param arrB 需要转换的byte数组.
     * @return 转换后的字符串.
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出.
     */
    private static String byteArr2HexStr(byte[] arrB)
            throws Exception {
        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp += 256;
            }

            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB).
     * 互为可逆的转换过程.
     *
     * @param strIn 需要转换的字符串.
     * @return 转换后的byte数组.
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出.
     */
    private static byte[] hexStr2ByteArr(String strIn)
            throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    /**
     * 加密字符串 使用指定KEY
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static String encrypt(String data, String key)
            throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * 解密字符串使用指定KEY
     *
     * @param data
     * @param key
     * @return
     * @throws IOException
     * @throws Exception
     */
    private static String decrypt(String data, String key)
            throws IOException, Exception {
        if (data == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * 加密 字符串数据  使用指定KEY
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key)
            throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(DES);

        cipher.init(1, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 解密 字符串数据，使用指定KEY
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key)
            throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(DES);

        cipher.init(2, securekey, sr);

        return cipher.doFinal(data);
    }


}
