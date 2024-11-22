package com.kuit.kuit4serverauth;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32];
        random.nextBytes(values);
        return Base64.getEncoder().encodeToString(values);
    }

    public static void main(String[] args) {
        String secureKey = generateSafeToken();
        System.out.println("Secure Key: " + secureKey);
    }
}
