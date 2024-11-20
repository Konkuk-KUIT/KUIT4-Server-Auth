package com.kuit.kuit4serverauth;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    /*public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[64]; // 512 bits
        random.nextBytes(values);
        String secureKey = Base64.getEncoder().encodeToString(values);
        System.out.println("Secure Key: " + secureKey);
    }*/
    public static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32]; // 256 bits are usually enough
        random.nextBytes(values);
        return Base64.getEncoder().encodeToString(values);
    }

    public static void main(String[] args) {
        String secureKey = generateSafeToken();
        System.out.println("Secure Key: " + secureKey);
    }
}
