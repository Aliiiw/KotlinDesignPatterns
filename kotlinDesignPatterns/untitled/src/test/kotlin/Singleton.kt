import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*

only one instance
single point of access (network manager, database , ...

 */
object NetworkDriver {
    init {
        println("init $this")
    }

    fun log(): NetworkDriver = apply {
        println("network driver $this")
    }
}

class SingletonTest {
    @Test
    fun testSingleton() {
        println("start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        Assertions.assertThat(networkDriver1).isSameAs(NetworkDriver)
        Assertions.assertThat(networkDriver2).isSameAs(NetworkDriver)
    }

    // just create Object rather than class
}