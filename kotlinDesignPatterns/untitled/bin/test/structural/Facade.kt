package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*

provide simple interface to a complex functionality
it will remove the need for complex object / memory management -> don't care :)

 */


class ComplexSystemStore(private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from the file: $filePath")
        cache = HashMap()
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val username: String)


// Facade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.username)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade() {
        val userRepo = UserRepository()
        val user = User("john")
        userRepo.save(user)

        val retrievedUser = userRepo.findFirst()

        Assertions.assertThat(retrievedUser.username).isEqualTo("john")
    }
}