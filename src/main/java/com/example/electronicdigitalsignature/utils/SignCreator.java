package com.example.electronicdigitalsignature.utils;

import com.example.electronicdigitalsignature.data.KeyPair;
import com.example.electronicdigitalsignature.data.Sign;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SignCreator {
    /**
     * Генерация подписи
     *
     * @param message - Сообщение
     * @param keyPair - Пара ключей
     * @return Подпись
     */
    public static Sign createSign(String message, KeyPair keyPair) {
        BigInteger q = keyPair.getPublicKey().getQ();
        BigInteger p = keyPair.getPublicKey().getP();
        BigInteger g = keyPair.getPublicKey().getG();
        BigInteger w = keyPair.getPrivateKey().getW();
        SecureRandom sr = new SecureRandom();
        BigInteger r, x, s2, s1;
        r = new BigInteger(q.bitLength(), sr);
        x = g.modPow(r, p);
        byte[] digest = Hasher.hash(x, message);
        s1 = new BigInteger(1, digest);
        s2 = (r.subtract(w.multiply(s1))).mod(q);
        return new Sign(message, s1, s2);
    }
}
