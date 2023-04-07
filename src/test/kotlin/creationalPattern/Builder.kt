package creationalPattern

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }

        fun build() = Component(this)

        fun getParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
    }

    init {
        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}

class ComponentTest {

    @Test
    fun builderTest() {
        val component = Component.Builder()
            .setParam1("Hello")
            .setParam2(100)
            .setParam3(true)
            .build()

        Assertions.assertThat(component.param1).isEqualTo("Hello")
        Assertions.assertThat(component.param2).isEqualTo(100)
    }
}