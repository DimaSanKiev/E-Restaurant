package com.bionic.edu.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class Crypto {

    public static String encrypt(String password) {
        byte[] pwBytes = DigestUtils.sha(password);
        return Base64.encodeBase64String(pwBytes);
    }

    public static void main(String[] args) {
        String pass = "password";
        String encryptPass = encrypt(pass);
        System.out.println(pass + " " + encryptPass);
    }

}
