package xyz.le30r.view

import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*
import xyz.le30r.controller.ApplicationController
import xyz.le30r.data.KeyPair
import xyz.le30r.data.Sign
import xyz.le30r.utils.generateImage
import java.math.BigInteger

class ValidationView : View() {
    val controller: ApplicationController by inject()
    private var p: TextField by singleAssign()
    private var q: TextField by singleAssign()
    private var g: TextField by singleAssign()
    private var y: TextField by singleAssign()
    private var s1: TextField by singleAssign()
    private var s2: TextField by singleAssign()
    var message: TextArea by singleAssign()
    var result: Boolean = false
    var validLabel: Label by singleAssign()
    private var img: ImageView by singleAssign()
    override val root = form {
        hbox {
            vbox {
                fieldset("Публичный ключ") {
                    field("p:") {
                        p = textfield()
                    }
                    field("q:") {
                        q = textfield()
                    }
                    field("g:") {
                        g = textfield()
                    }
                    field("y:") {
                        y = textfield()
                    }
                }
                paddingAll = 10.0
            }

            vbox {
                fieldset(labelPosition = Orientation.VERTICAL) {
                    field("Сообщение", Orientation.VERTICAL) {
                        message = textarea {
                            prefRowCount = 5
                            vgrow = Priority.ALWAYS
                        }
                    }
                }
                fieldset("Подпись") {
                    field("s1:") {
                        s1 = textfield()
                    }
                    field("s2:") {
                        s2 = textfield()
                    }
                    button("Проверить подпись") {
                        action {
                            result = controller.checkSign(q, p, g, y, s1, s2, message)
                            img.image = SwingFXUtils.toFXImage(generateImage(BigInteger(s1.text), BigInteger(s2.text), message.text), null)
                            if (result) {
                                validLabel.text = "Подпись верна"
                                validLabel.textFill = Color.GREEN
                            } else {
                                validLabel.text = "Подпись неверна"
                                validLabel.textFill = Color.RED
                            }
                        }
                    }
                    validLabel = label()
                }
                paddingAll = 10.0
            }
            vbox {
                img = imageview() {
                    image = SwingFXUtils.toFXImage(generateImage(BigInteger.ONE, BigInteger.ONE, "test"), null)
                }
            }
        }
    }

    fun setFields(
        pair: KeyPair,
        message: String,
        sign: Sign
    ) {
        q.text = pair.publicKey.q.toString()
        p.text = pair.publicKey.p.toString()
        g.text = pair.publicKey.g.toString()
        y.text = pair.publicKey.y.toString()
        this.message.text = message
        s1.text = sign.s1.toString()
        s2.text = sign.s2.toString()
    }
}