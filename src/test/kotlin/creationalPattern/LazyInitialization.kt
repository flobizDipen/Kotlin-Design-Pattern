package creationalPattern

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this : $message")
    }
}

class Window1 {
    val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest {

    @Test
    fun window1Test() {
        val window1 = Window1()
        window1.showMessage("Lazy Initialization")
        Assertions.assertThat(window1.box).isNotNull
    }

    @Test
    fun window2Test() {
        val window2 = Window2()
        window2.showMessage("Lateinit Initialization")
        Assertions.assertThat(window2.box).isNotNull
    }
}