package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*
* Bridge
* - having classes with multiple orthogonal traits exponentially increases the size of the inheritance.
* - Split into multiple interface/ classes associated them using a "bridge" reference.
* */

interface Device {
    var volume: Int
    fun getName(): String
}


class Radio : Device {
    override var volume: Int = 0
    override fun getName() = "Radio $this"
}

class TV : Device {
    override var volume: Int = 0
    override fun getName() = "Radio $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}


// Here we pass device as a bridge.
class BasicRemote(val device: Device) : Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume up ${device.volume}")
    }
}

class BridgeTest {

    @Test
    fun testBridge() {
        val tv = TV()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeDown()

        Assertions.assertThat(tv.volume).isEqualTo(1)
        Assertions.assertThat(radio.volume).isEqualTo(2)
    }
}