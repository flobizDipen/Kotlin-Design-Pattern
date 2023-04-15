package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Facade :
 * - Provide simple interface to a complex functionality.
 * - Remove the need for the complex object / memory management.
 * - Simply client implementation.
 */

class ComplexSystemStore(private val filePath: String) {

    private val cache: HashMap<String, String>

    init {
        println("Reading data from the file :$filePath")
        cache = HashMap()
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val login: String)


//Facade
class UserRepository {
    private val systemPreference = ComplexSystemStore("/data/default.pref")

    fun save(user: User) {
        systemPreference.store("USER_KEY", user.login)
        systemPreference.commit()
    }

    fun findFirst(): User = User(systemPreference.read("USER_KEY"))
}

class FacadeTest {

    @Test
    fun testFacade() {
        val userRepository = UserRepository()
        val user = User("Dipen")
        userRepository.save(user)
        val retrieveUser = userRepository.findFirst()

        Assertions.assertThat(retrieveUser.login).isEqualTo("Dipen")
    }
}
