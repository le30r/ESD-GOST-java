package xyz.le30r.utils

import xyz.le30r.data.PublicKey
import xyz.le30r.data.Sign
import xyz.le30r.utils.Hasher.hash
import java.math.BigInteger

object SignChecker {
    /**
     * Проверка подписи
     *
     * @param sign      - Подпись
     * @param publicKey - Публичный ключ
     * @return - true если подпись подлинная, иначе false
     */
    @JvmStatic
    fun checkSign(sign: Sign, publicKey: PublicKey): Boolean {
        val p = publicKey.p
        val g = publicKey.g
        val y = publicKey.y
        val s1 = sign.s1
        val s2 = sign.s2
        val x1 = g.modPow(s2, p)
        val x2 = y.modPow(s1, p).mod(p)
        val x = x1.multiply(x2).mod(p)
        val digest = hash(x, sign.message)
        val HH = BigInteger(1, digest)
        return s1 == HH
    }
}