package xyz.le30r.view

import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Orientation
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Priority
import tornadofx.*
import xyz.le30r.controller.ApplicationController
import xyz.le30r.utils.Hasher
import xyz.le30r.utils.generateImage
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.math.BigInteger
import javax.imageio.ImageIO
import javax.swing.ImageIcon

class InputView : View() {
    val controller: ApplicationController by inject()
    private var w: TextField by singleAssign()
    private var p: TextField by singleAssign()
    private var q: TextField by singleAssign()
    private var g: TextField by singleAssign()
    private var y: TextField by singleAssign()
    private var s1: TextField by singleAssign()
    private var s2: TextField by singleAssign()
    private var img: ImageView by singleAssign()
    var message: TextArea by singleAssign()
    override val root = form {
        hbox {
            vbox {
                fieldset("Приватный ключ") {
                    field("w:") {
                        w = textfield()
                    }
                }
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
                    button("Ген. пару ключей") {
                        action {
                            controller.generateKeyPair(q, p, g, y, w)
                        }
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
                    button("Подписать") {
                        action {
                            controller.sign(message, s1, s2)
                            img.image = SwingFXUtils.toFXImage(generateImage(BigInteger(s1.text), BigInteger(s2.text), message.text), null)
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


}