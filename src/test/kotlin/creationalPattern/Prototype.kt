package creationalPattern

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

abstract class Shape : Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null

        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}


class Rectangle : Shape() {
    override fun draw() {
        println("Inside Rectangle Draw")
    }

    init {
        type = "Rectangle"
    }

}

class Square : Shape() {
    override fun draw() {
        println("Inside Square Draw")
    }

    init {
        type = "Square"
    }
}

object ShapeCache {
    private var shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        val square = Square()
        val rectangle = Rectangle()

        shapeMap["1"] = square
        shapeMap["2"] = rectangle
    }

    fun getShape(shapeId: String): Shape {
        val cacheShape = shapeMap[shapeId]
        return cacheShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testProtoType() {
        ShapeCache.loadCache()

        val clonedShape1 = ShapeCache.getShape("1")
        val clonedShape2 = ShapeCache.getShape("2")

        clonedShape1.draw()
        clonedShape2.draw()

        Assertions.assertThat(clonedShape1.type).isEqualTo("Square")
        Assertions.assertThat(clonedShape2.type).isEqualTo("Rectangle")
    }

}