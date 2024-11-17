package com.kuit.kuit4serverauth.service;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    public static String generateKey(){
        byte[] key = new byte[32]; // 256비트
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
