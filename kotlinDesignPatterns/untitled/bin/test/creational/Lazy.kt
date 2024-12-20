package creational

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


/*

for memory management

 */


class AlertBox {
    var message: String? = null

    fun show() {
        println("creational.AlertBox $this: $message")
    }
}

class Window {
    // the lazy should be val, if you need var you need to use late init var
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
    fun windowTest() {
        val window = Window()
        window.showMessage("Hello window")
        Assertions.assertThat(window.box).isNotNull

        val window2 = Window2()
//        println(window2.box)  error not initialised
        window2.showMessage("Second window")
        Assertions.assertThat(window2.box).isNotNull
    }
}