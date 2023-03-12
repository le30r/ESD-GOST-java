package xyz.le30r.data

import java.math.BigInteger

class PrivateKey(var w: BigInteger) {

    override fun toString(): String {
        val sb = StringBuilder("PrivateKey{")
        sb.append("w=").append(w)
        sb.append('}')
        return sb.toString()
    }
}