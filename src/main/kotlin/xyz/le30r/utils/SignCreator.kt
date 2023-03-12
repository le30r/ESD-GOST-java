package xyz.le30r.utils

import xyz.le30r.data.KeyPair
import xyz.le30r.data.Sign
import xyz.le30r.utils.Hasher.hash
import java.math.BigInteger
import java.security.SecureRandom

object SignCreator {
    /**
     * Генерация подписи
     *
     * @param message - Сообщение
     * @param keyPair - Пара ключей
     * @return Подпись
     */
    @JvmStatic
    fun createSign(message: String?, keyPair: KeyPair): Sign {
        val q = keyPair.publicKey.q
        val p = keyPair.publicKey.p
        val g = keyPair.publicKey.g
        val w = keyPair.privateKey.w
        val sr = SecureRandom()
        val r: BigInteger
        val x: BigInteger
        val s2: BigInteger
        val s1: BigInteger
        r = BigInteger(q.bitLength(), sr)
        x = g.modPow(r, p)
        val digest = hash(x, message!!)
        s1 = BigInteger(1, digest)
        s2 = r.subtract(w.multiply(s1)).mod(q)
        return Sign(message, s1, s2)
    }
}