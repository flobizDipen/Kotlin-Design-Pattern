package structural

import org.junit.jupiter.api.Test

/**
 * Proxy Design Pattern :
 * - Provide some functionality before and/or after calling an object.
 * - It is similar as Facade, except the proxy has the same interface.
 * - It is also similar to Decorator, except the proxy manages the lifecycle of its object. Whereas in Decorator
 * client manages the object.
 */
interface Image {
    fun display()
}

class RealImage(private val fileName: String) : Image {
    override fun display() {
        println("RealImage : Display Image : $fileName")
    }

    private fun loadFromDisk(fileName: String) {
        println("RealImage : Loading $fileName")
    }

    init {
        loadFromDisk(fileName)
    }
}

class ProxyImage(private val fileName: String) : Image {

    private var realImage: RealImage? = null
    override fun display() {
        println("ProxyImage : Displaying $fileName")
        if (realImage == null)
            realImage = RealImage(fileName)
        realImage?.display()
    }

}

class ProxyTest {

    @Test
    fun testProxy() {
        val image = ProxyImage("test.jpeg")
        //load image from the disk
        image.display()
        println("-------------------")

        //load image from cache
        image.display()
    }
}