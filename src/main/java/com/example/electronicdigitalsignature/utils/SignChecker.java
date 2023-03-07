package com.example.electronicdigitalsignature.utils;

import com.example.electronicdigitalsignature.data.PublicKey;
import com.example.electronicdigitalsignature.data.Sign;

import java.math.BigInteger;

public class SignChecker {
    /**
     * Проверка подписи
     *
     * @param sign      - Подпись
     * @param publicKey - Публичный ключ
     * @return - true если подпись подлинная, иначе false
     */
    public static boolean checkSign(Sign sign, PublicKey publicKey) {
        BigInteger p = publicKey.getP();
        BigInteger g = publicKey.getG();
        BigInteger y = publicKey.getY();

        BigInteger s1 = sign.getS1();
        BigInteger s2 = sign.getS2();

        BigInteger x1 = g.modPow(s2, p);
        BigInteger x2 = (y.modPow(s1, p)).mod(p);
        BigInteger x = x1.multiply(x2).mod(p);

        byte[] digest = Hasher.hash(x, sign.getMessage());
        BigInteger HH = new BigInteger(1, digest);
        return s1.equals(HH);
    }
}
