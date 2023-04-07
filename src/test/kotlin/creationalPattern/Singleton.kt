package creationalPattern

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

object NetworkDriver {
    init {
        println("Initializing : $this")
    }

    fun log(): NetworkDriver = apply { println("Network Driver : $this") }
}

class SingletonTest {

    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1: NetworkDriver = NetworkDriver.log()
        val networkDriver2: NetworkDriver = NetworkDriver.log()

        Assertions.assertThat(networkDriver1).isSameAs(networkDriver2)
        Assertions.assertThat(networkDriver2).isSameAs(networkDriver1)
    }
}