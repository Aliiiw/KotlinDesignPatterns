package structural

import org.junit.jupiter.api.Test


/*

provide some functionality before and/or after calling an object

 */


interface Image {
    fun display()
}

class RealImage(private val filename: String) : Image {
    override fun display() {
        println("RealImage: Displaying $filename")
    }

    private fun loadFromDisk(filename: String) {
        println("RealImage: Loading $filename")
    }

    init {
        loadFromDisk(filename)
    }
}

class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        println("ProxyImage: Displaying $filename")
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage!!.display()
    }
}

class ProxyTest {
    @Test
    fun testProxy() {
        val image = ProxyImage("test.jpg")

        // load image from disk
        image.display()
        println("-------------------")

        //load image from cache
        image.display()
    }
}