package com.bionic.edu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class _PasswordHashing {

    Map<String, String> DB = new HashMap<String, String>();
    public static final String SALT = "my-salt-text";

    public static void main(String args[]) {
        _PasswordHashing demo = new _PasswordHashing();
        demo.signup("john", "dummy123");

        // login should succeed.
        if (demo.login("john", "dummy123"))
            System.out.println("user login successfull.");

        // login should fail because of wrong password.
        if (demo.login("john", "blahblah"))
            System.out.println("User login successfull.");
        else
            System.out.println("user login failed.");
    }

    public void signup(String username, String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        DB.put(username, hashedPassword);
    }

    public Boolean login(String username, String password) {
        Boolean isAuthenticated = false;

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        System.out.println("SALT:" + SALT);
        System.out.println("password:" + password);
        System.out.println("salted:" + saltedPassword);

        String storedPasswordHash = DB.get(username);
        if (hashedPassword.equals(storedPasswordHash)) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }
        return hash.toString();
    }

}