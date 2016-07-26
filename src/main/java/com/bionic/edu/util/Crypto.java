package com.bionic.edu.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * The {@code Crypto} class is used to encrypt users' passwords
 * to persist them into the database.
 */
public class Crypto {

    /**
     * Converts a string into bytes with SHA algorithm using Apache Commons Codec library,
     * then encodes this binary data to a string using the Base64 algorithm.
     *
     * @param password String to encode
     * @return String containing Base64 characters
     * @see <a href="https://commons.apache.org/proper/commons-codec/">Apache Commons Codec</a>.
     */
    public static String encrypt(String password) {
        byte[] pwBytes = DigestUtils.sha(password);
        return Base64.encodeBase64String(pwBytes);
    }

    // Testing encrypt() method, output to console
    public static void main(String[] args) {
        String pass = "password";
        String encryptPass = encrypt(pass);
        System.out.println(pass + " " + encryptPass);
    }

}
