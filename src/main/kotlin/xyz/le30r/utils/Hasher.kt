package xyz.le30r.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Класс для вычисления значения хеш-функции
 */
object Hasher {
    /**
     * Вычисление значения хеш-функции
     *
     * @param x  - сгенерированное число x=g^r (mod p)
     * @param message - Сообщение
     */
    @JvmStatic
    fun hash(x: BigInteger, message: String): ByteArray {
        var md5: MessageDigest? = null
        try {
            md5 = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        md5!!.update(message.toByteArray())
        md5.update(x.toString().toByteArray())
        return md5.digest()
    }
}