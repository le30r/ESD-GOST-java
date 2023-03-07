package com.example.electronicdigitalsignature.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс для вычисления значения хеш-функции
 */
public class Hasher {
    /**
     * Вычисление значения хеш-функции
     *
     * @param x  - сгенерированное число x=g^r (mod p)
     * @param message - Сообщение
     */
    public static byte[] hash(BigInteger x, String message) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(message.getBytes());
        md5.update(x.toString().getBytes());
        return md5.digest();
    }
}
