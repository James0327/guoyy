package com.jw.algorithm.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * guoyy com.jw.algorithm.rsa
 *
 * @Description: com.jw.algorithm.rsa.RSAEncrypt
 * @Author: guoyiyong/james
 * @Date: 2020-07-29 21:53
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class RSAEncrypt {

    public static void main(String[] args) throws Exception {
        String content = "中国";

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey pubK = keyPair.getPublic();
        PrivateKey priK = keyPair.getPrivate();
        String pubKey = Base64.getEncoder().encodeToString(pubK.getEncoded());
        String priKey = Base64.getEncoder().encodeToString(priK.getEncoded());

        System.out.println("pubKey: " + pubKey);
        System.out.println("priKey: " + priKey);

        byte[] pub = Base64.getDecoder().decode(pubKey);
        byte[] pri = Base64.getDecoder().decode(priKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(pub));

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] data = cipher.doFinal(content.getBytes());
        String encrypt = Base64.getEncoder().encodeToString(data);
        System.out.println("encrypt: " + encrypt);

        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pri));
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encrypt));

        System.out.println(new String(decrypt));
    }

}
