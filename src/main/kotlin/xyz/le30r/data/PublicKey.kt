package xyz.le30r.data

import java.math.BigInteger

class PublicKey(var p: BigInteger, var q: BigInteger, var g: BigInteger, var y: BigInteger) {

    override fun toString(): String {
        val sb = StringBuilder("PublicKey{")
        sb.append("p=").append(p)
        sb.append(", q=").append(q)
        sb.append(", g=").append(g)
        sb.append(", y=").append(y)
        sb.append('}')
        return sb.toString()
    }
}