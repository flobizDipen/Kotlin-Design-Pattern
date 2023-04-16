package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * State Design Pattern:
 * - An object changes its behaviour based on the internal state.
 * - At any moment, there's finite number of states a program can be in.
 * - State can be encapsulated in object.
 */

sealed class AuthorizationState

object UnAuthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = UnAuthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is UnAuthorized -> false
        }

    val userName: String
        get() = when (val state = this.state) {
            is Authorized -> state.userName
            is UnAuthorized -> "Unknown"
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = UnAuthorized
    }

    override fun toString(): String = "User $userName is logged in  : $isAuthorized"
}

class StateTest {

    @Test
    fun testState() {
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.loginUser("Admin")
        println(authorizationPresenter)

        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        Assertions.assertThat(authorizationPresenter.userName).isEqualTo("Admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)

        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
    }
}