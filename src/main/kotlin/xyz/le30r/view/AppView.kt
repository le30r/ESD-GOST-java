package xyz.le30r.view


import tornadofx.*
import xyz.le30r.controller.ApplicationController

class AppView : View() {
    val controller: ApplicationController by inject()
    private val input = InputView()
    private val validation = ValidationView()
    override val root = borderpane {
        top = input.root
        center = button("Копировать >>") {
            action {
                validation.setFields(
                    controller.pair,
                    input.message.text,
                    controller.sign
                )
            }
        }
        bottom = validation.root

    }
}