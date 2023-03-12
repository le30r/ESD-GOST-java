package xyz.le30r.data

class KeyPair(var publicKey: PublicKey, var privateKey: PrivateKey) {

    override fun toString(): String {
        val sb = StringBuilder("KeysPair{")
        sb.append("publicKey=").append(publicKey)
        sb.append(", privateKey=").append(privateKey)
        sb.append('}')
        return sb.toString()
    }
}