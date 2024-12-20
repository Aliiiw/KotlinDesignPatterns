package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*

compose objects into tree structures
works when the core functionality can be represented as a tree

 */


open class Equipment(
    open val price: Int,
    val name: String,
)

open class Composite(name: String) : Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.sumOf { it.price }

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class Computer : Composite("PC")
class Processor : Equipment(1000, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Composite("Memory")
class ROM : Equipment(100, "Read Only Memory")
class RAM : Equipment(75, "Random Access Memory")

class CompositeTest {
    @Test
    fun testComposite() {
        val memory = Memory()
            .add(ROM())
            .add(RAM())
        val pc = Computer()
            .add(memory)
            .add(Processor())
            .add(HardDrive())
        println("PC price: ${pc.price}")

        Assertions.assertThat(pc.name).isEqualTo("PC")
        Assertions.assertThat(pc.price).isEqualTo(1425)
    }
}