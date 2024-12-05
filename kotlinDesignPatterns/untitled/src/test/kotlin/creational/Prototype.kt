package creational

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


/*

copy existing objects
the object have copy methods

 */
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
        println("Inside creational.Rectangle::draw() method.")
    }

    init {
        type = "creational.Rectangle"
    }
}

class Square : Shape() {
    override fun draw() {
        println("Inside creational.Square::draw() method.")
    }

    init {
        type = "creational.Square"
    }
}

class Circle : Shape() {
    override fun draw() {
        println("Inside creational.Circle::draw() method.")
    }

    init {
        type = "creational.Circle"
    }
}

object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        val circle = Circle()
        val square = Square()
        val rectangle = Rectangle()

        shapeMap["1"] = circle
        shapeMap["2"] = square
        shapeMap["3"] = rectangle
    }

    fun getShape(shapeId: String): Shape {
        val cachedShape = shapeMap[shapeId]
        return cachedShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testPrototype() {
        ShapeCache.loadCache()
        val clonedShape1 = ShapeCache.getShape("1")
        val clonedShape2 = ShapeCache.getShape("2")
        val clonedShape3 = ShapeCache.getShape("3")

        clonedShape1.draw()
        clonedShape2.draw()
        clonedShape3.draw()

        Assertions.assertThat(clonedShape1.type).isEqualTo("creational.Circle")
        Assertions.assertThat(clonedShape2.type).isEqualTo("creational.Square")
        Assertions.assertThat(clonedShape3.type).isEqualTo("creational.Rectangle")
    }
}