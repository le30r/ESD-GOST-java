package xyz.le30r

import tornadofx.App
import tornadofx.launch
import xyz.le30r.view.AppView

class DigitalSignatureApplication : App(AppView::class)

fun main(args: Array<String>) {
    launch<DigitalSignatureApplication>(args)
}