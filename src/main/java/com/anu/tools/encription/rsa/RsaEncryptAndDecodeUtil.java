package com.anu.tools.encription.rsa;

import com.anu.tools.utlis.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Describe:
 * Author: anu
 * Mail: anhui_gaoxu@126.com
 * Phone: 18355150480
 * Date: 2019年05月29日 15:02
 * Copyright: © 2019.Anu Studio., Ltd. All rights reserved.
 */
public class RsaEncryptAndDecodeUtil {

    private static final String RSA = "RSA";

    private static final String encoding = "UTF-8";

    /**
     * 公钥(用于解密)
     */
    private static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChqi7nCgZUnA2EYFqDu4RaYK5AA0NzQbDPdOimyxzhYoUWt+/5dktxGv30QNiuZg3DcFhPFoUNSAj9oZyn7+qGD03V7gV8c7+FqMtdXEYR+7uf4W9bn7FAyVk1S/QRvJK2UwfxEDiHnb+WupRX/KF9aAYmbqv8BxOFUMUoqAekLwIDAQAB";

    /**
     * 私钥加密(用于加密)
     */
    private static final String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKGqLucKBlScDYRgWoO7hFpgrkADQ3NBsM906KbLHOFihRa37/l2S3Ea/fRA2K5mDcNwWE8WhQ1ICP2hnKfv6oYPTdXuBXxzv4Woy11cRhH7u5/hb1ufsUDJWTVL9BG8krZTB/EQOIedv5a6lFf8oX1oBiZuq/wHE4VQxSioB6QvAgMBAAECgYAtMtqIJ+pbL4Ir95B28i9v4UX2ibMK/1vCtMnzECiT7yqMjoKeeW9gQQ6tgo/SOqAwezGFcyNh8jjpSIz2+wHTZHnpwX0EtcXYINVCSASECR2EE2vneNZgZ+6q8aXtTxeEZAz9QygArrEhxVjrGvcOdirAA9P+CLylHT+9z7uweQJBAOq5r7PfOPMnp8qSgPvwUK6mz6dAxx3BlQFyW2ZqKAcLWM2o9+53JrIfdfevOaK3G+RZpbELEW5Id2H4EztUSVMCQQCwUUdHM7cpUcXiK68h+A+24T3rIaTbm4RnoxO7+avw1xujKrq0gijmH5HTeDRNVsQUip6TaBULzBOQl92ILPI1AkEA2/ZMoCwUa1EDHYNzGiWXBkROHjdHISxynVt0KaJbhlZszrWEOdUwzwVKgGSVh1U+7xvPePydRhLkxUs7goK2/wJALu+UD+o+aetwyAJk/p/wXvia6QrtveAqYQRSbonKMbSxkMABL6guhwQEZ8zp72SXkfhMumTMpXVQB//FY0jDuQJBAK/zTg6CMAN+/irjL1zjZYa21irmgxhid0VXHrjGCejyFEq0fzFqT31VkjYroNuSfDgOoNYAl+OFsVCEQIj6ip4=";


    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        return encrypt(RSA_PRIVATE_KEY, data.trim());
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        return decrypt(RSA_PUBLIC_KEY, data.trim());
    }

    /**
     * 使用私钥加密
     *
     * @param key
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encrypt(String key, String plainText) throws Exception {
        if (key == null || "".equals(key.trim()) || key.length() <= 0) {
            key = RSA_PRIVATE_KEY;
        }

        byte[] keyBytes = Base64Utils.base64ToByteArray(key);
        return encrypt(keyBytes, plainText);
    }

    public static String encrypt(byte[] keyBytes, String plainText) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = factory.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance(RSA);

        try {
            cipher.init(1, privateKey);
        } catch (InvalidKeyException var10) {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
            Key fakePublicKey = KeyFactory.getInstance(RSA).generatePublic(publicKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(1, fakePublicKey);
        }

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(encoding));
        String encryptedString = Base64Utils.byteArrayToBase64(encryptedBytes);
        return encryptedString;
    }


    /**
     * 公钥解密
     *
     * @param publicKeyText
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decrypt(String publicKeyText, String cipherText) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyText);
        return decrypt(publicKey, cipherText);
    }

    /**
     * 获取公钥
     *
     * @param publicKeyText
     * @return
     */
    public static PublicKey getPublicKey(String publicKeyText) {
        if (publicKeyText == null || publicKeyText.length() == 0) {
            publicKeyText = RSA_PUBLIC_KEY;
        }

        try {
            byte[] publicKeyBytes = Base64Utils.base64ToByteArray(publicKeyText);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePublic(x509KeySpec);
        } catch (Exception var4) {
            throw new IllegalArgumentException("Failed to get public key", var4);
        }
    }


    public static String decrypt(PublicKey publicKey, String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);

        try {
            cipher.init(2, publicKey);
        } catch (InvalidKeyException var7) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
            Key fakePrivateKey = KeyFactory.getInstance(RSA).generatePrivate(spec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(2, fakePrivateKey);
        }

        if (cipherText != null && cipherText.length() != 0) {
            byte[] cipherBytes = Base64Utils.base64ToByteArray(cipherText);
            byte[] plainBytes = cipher.doFinal(cipherBytes);
            return new String(plainBytes);
        } else {
            return cipherText;
        }
    }

    /**
     * 生成公钥和私钥对
     *
     * @param keySize 秘钥位数
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[][] genKeyPairBytes(int keySize) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = new byte[2][];
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(keySize, new SecureRandom());
        KeyPair pair = gen.generateKeyPair();
        keyPairBytes[0] = pair.getPrivate().getEncoded();
        keyPairBytes[1] = pair.getPublic().getEncoded();
        return keyPairBytes;
    }

    /**
     * 生成公钥和私钥对
     *
     * @param keySize 秘钥位数
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String[] genKeyPair(int keySize) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = genKeyPairBytes(keySize);
        String[] keyPairs = new String[]{Base64Utils.byteArrayToBase64(keyPairBytes[0]), Base64Utils.byteArrayToBase64(keyPairBytes[1])};
        return keyPairs;
    }


    public static void main(String[] args) throws Exception {
        int count = 100;
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
        System.out.println(decode("emL9J4h5xuAan41z8j3qFRMbng0NI6YfyjyWBLf08iLgu/LtiaDG93ODf+DmXws5v3/EFdIRfKZVU0mCl/fpMY4Xz58QI9ypk2sAUzCkX+c55fpse2UQR1EB+mshR8lG2k7bu4w2dALIQiggCB4UStjcj02FapVXaUhO0MTB1RQ="));
        System.out.println(5);
        System.out.println(decode("e/1NyRy+3trzGYPVhPIvBtIx+c/3r0h6PpW3SR0zQ4w7jXPsE6+C+CpZzCe8y5N/baAenopjO8JlMEWvUkfg5U3c/URLU67FAJWiiIS3fK+EhOBazD3Dv4x2IYwYPGkC1pTpJU81b/GkE+q2JL8xUD0jAO1o7MaF1VS0row3WD0="));
        System.out.println(6);
        System.out.println(decode("IpLmoSQaUW0H38JG0/iQpa9+aVGKphyl/ccNti/QKtdmh1NxLeqO2sIiVOeGL31uBT8rWSXvy5qjrZGLhuAHaJNT9hocs+x6/+KHaRjKpZtusjpgg4o/U5j7VETHj6QhvZk8D8tYJys5sVAyfZ3ciPNsGaXEfpw1oj84TOthb00="));
        System.out.println(7);
        data = "jdbc:mysql:tcp://127.0.0.1:3306/test?charset-encoding=UTF-8";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            encode(data);
        }
        endTime = System.currentTimeMillis();
        System.out.println("加密" + count + "次需要" + (endTime - startTime) + "毫秒。");

        data = "IpLmoSQaUW0H38JG0/iQpa9+aVGKphyl/ccNti/QKtdmh1NxLeqO2sIiVOeGL31uBT8rWSXvy5qjrZGLhuAHaJNT9hocs+x6/+KHaRjKpZtusjpgg4o/U5j7VETHj6QhvZk8D8tYJys5sVAyfZ3ciPNsGaXEfpw1oj84TOthb00=";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            decode(data);
        }
        endTime = System.currentTimeMillis();
        System.out.println("解密" + count + "次需要" + (endTime - startTime) + "毫秒。");
    }

}
