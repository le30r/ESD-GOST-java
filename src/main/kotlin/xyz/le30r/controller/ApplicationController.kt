package xyz.le30r.controller

import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import tornadofx.*
import xyz.le30r.data.KeyPair
import xyz.le30r.data.PublicKey
import xyz.le30r.data.Sign
import xyz.le30r.utils.KeyGenerator
import xyz.le30r.utils.SignChecker
import xyz.le30r.utils.SignCreator
import java.math.BigInteger

class ApplicationController : Controller() {
    lateinit var pair: KeyPair
    lateinit var sign: Sign
    fun generateKeyPair(
        q: TextField, p: TextField, g: TextField, y: TextField, w: TextField
    ) {
        pair = KeyGenerator.generateKeyPair()
        with(pair.publicKey) {
            q.text = this.q.toString()
            p.text = this.p.toString()
            g.text = this.g.toString()
            y.text = this.y.toString()
        }
        with(pair.privateKey) {
            w.text = this.w.toString()
        }
    }

    fun sign(
        message: TextArea, s1: TextField, s2: TextField
    ) {
        sign = SignCreator.createSign(message.text, pair)
        s1.text = sign.s1.toString()
        s2.text = sign.s2.toString()
    }

    fun checkSign(
        q: TextField, p: TextField, g: TextField, y: TextField, s1: TextField, s2: TextField, message: TextArea
    ): Boolean {
        val sign = Sign(
            message.text, BigInteger(s1.text), BigInteger(s2.text)
        )
        val publicKey = PublicKey(
            BigInteger(p.text), BigInteger(q.text), BigInteger(g.text), BigInteger(y.text)
        )
        return SignChecker.checkSign(sign, publicKey)
    }
}