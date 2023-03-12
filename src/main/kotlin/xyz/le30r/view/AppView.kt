package xyz.le30r.view

import javafx.scene.Parent
import tornadofx.*

class AppView : View() {
    override val root =
        borderpane {
            top<TopView>()
            bottom<BottomView>()
    }
}

class TopView: View() {
    override val root = label("Top View")
}


class BottomView: View() {
    override val root = label("Bottom View")
}