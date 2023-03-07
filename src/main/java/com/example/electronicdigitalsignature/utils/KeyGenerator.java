package com.example.electronicdigitalsignature.utils;

import com.example.electronicdigitalsignature.data.KeyPair;
import com.example.electronicdigitalsignature.data.PrivateKey;
import com.example.electronicdigitalsignature.data.PublicKey;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * Класс генератор пары ключей
 */
public class KeyGenerator {
    /**
     * Битовая длина q
     */
    private static final int Q_BIT_LENGTH = 1024;
    /**
     * Уверенность в том что генерируемое число простое.
     * Вероятность того что число простое равна 1 - (1/2)^certainty
     */
    private static final int CERTAINTY = 100;
    private static final BigInteger BIG_INTEGER_TWO = new BigInteger("2");
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Метод для генерации пары ключей
     *
     * @return возвращает объект пары ключей
     */
    public static KeyPair generateKeyPair() {
        BigInteger q = generateQ();
        BigInteger p = generateP(q);
        BigInteger g = generateG(p, q);
        BigInteger w = new BigInteger(Q_BIT_LENGTH, RANDOM);
        BigInteger y = g.modPow(w, p);
        PublicKey publicKey = new PublicKey(p, q, g, y);
        PrivateKey privateKey = new PrivateKey(w);
        return new KeyPair(publicKey, privateKey);
    }

    /**
     * Метод генерации числа g
     */
    private static BigInteger generateG(BigInteger p, BigInteger q) {
        BigInteger g;
        BigInteger a;
        do {
            a = (BIG_INTEGER_TWO.add(new BigInteger(Q_BIT_LENGTH, CERTAINTY, RANDOM))).mod(p);
            BigInteger d = (p.subtract(BigInteger.ONE)).divide(q);
            g = a.modPow(d, p);
        } while (g.equals(BigInteger.ONE));
        return g;
    }

    /**
     * Метод генерации числа q
     *
     * @return q
     */
    private static BigInteger generateQ() {
        return new BigInteger(Q_BIT_LENGTH, CERTAINTY, RANDOM);
    }

    /**
     * Метод генерации числа p
     */
    private static BigInteger generateP(BigInteger q) {
        BigInteger qp = BigInteger.ONE;
        BigInteger p;
        do {
            p = q.multiply(qp).multiply(BIG_INTEGER_TWO).add(BigInteger.ONE);
            if (p.isProbablePrime(CERTAINTY)) break;
            qp = qp.add(BigInteger.ONE);
        } while (true);
        return p;
    }
}
