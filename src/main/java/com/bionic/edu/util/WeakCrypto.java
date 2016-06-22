package com.bionic.edu.util;

public class WeakCrypto {

    // TODO: 6/21/16 - add password encryption
    public static String encrypt(String password) {
//        String cryptPassword = "";
//        for (char c : password.toCharArray()) {
//            cryptPassword += (char) (c ^ 51);
//        }
//        return cryptPassword;
        return password;
    }

    public static void main(String[] args) {
        String pass = "password";
        System.out.println(pass);
        String pass1 = encrypt(pass);
        System.out.println(pass1);
        String pass2 = encrypt(pass1);
        System.out.println(pass2);
        String pass3 = encrypt(pass2);
        System.out.println(pass3);
    }

}
