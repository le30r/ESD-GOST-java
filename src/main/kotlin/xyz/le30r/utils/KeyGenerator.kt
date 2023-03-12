package xyz.le30r.utils

import xyz.le30r.data.KeyPair
import xyz.le30r.data.PrivateKey
import xyz.le30r.data.PublicKey
import java.math.BigInteger
import java.security.SecureRandom

/**
 * Класс генератор пары ключей
 */
object KeyGenerator {
    /**
     * Битовая длина q
     */
    private const val Q_BIT_LENGTH = 1024

    /**
     * Уверенность в том что генерируемое число простое.
     * Вероятность того что число простое равна 1 - (1/2)^certainty
     */
    private const val CERTAINTY = 100
    private val BIG_INTEGER_TWO = BigInteger("2")
    private val RANDOM = SecureRandom()

    /**
     * Метод для генерации пары ключей
     *
     * @return возвращает объект пары ключей
     */
    @JvmStatic
    fun generateKeyPair(): KeyPair {
        val q = generateQ()
        val p = generateP(q)
        val g = generateG(p, q)
        val w = BigInteger(Q_BIT_LENGTH, RANDOM)
        val y = g.modPow(w, p)
        val publicKey = PublicKey(p, q, g, y)
        val privateKey = PrivateKey(w)
        return KeyPair(publicKey, privateKey)
    }

    /**
     * Метод генерации числа g
     */
    private fun generateG(p: BigInteger, q: BigInteger): BigInteger {
        var g: BigInteger
        var a: BigInteger
        do {
            a = BIG_INTEGER_TWO.add(BigInteger(Q_BIT_LENGTH, CERTAINTY, RANDOM))
                .mod(p)
            val d = p.subtract(BigInteger.ONE).divide(q)
            g = a.modPow(d, p)
        } while (g == BigInteger.ONE)
        return g
    }

    /**
     * Метод генерации числа q
     *
     * @return q
     */
    private fun generateQ(): BigInteger {
        return BigInteger(Q_BIT_LENGTH, CERTAINTY, RANDOM)
    }

    /**
     * Метод генерации числа p
     */
    private fun generateP(q: BigInteger): BigInteger {
        var qp = BigInteger.ONE
        var p: BigInteger
        do {
            p = q.multiply(qp).multiply(BIG_INTEGER_TWO).add(BigInteger.ONE)
            if (p.isProbablePrime(CERTAINTY)) break
            qp = qp.add(BigInteger.ONE)
        } while (true)
        return p
    }
}