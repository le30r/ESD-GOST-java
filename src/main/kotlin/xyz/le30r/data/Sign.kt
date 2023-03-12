package xyz.le30r.data

import java.math.BigInteger

class Sign(var message: String, var s1: BigInteger, var s2: BigInteger) {

    override fun toString(): String {
        val sb = StringBuilder("Sign{")
        sb.append("message='").append(message).append('\'')
        sb.append(", s1=").append(s1)
        sb.append(", s2=").append(s2)
        sb.append('}')
        return sb.toString()
    }
}