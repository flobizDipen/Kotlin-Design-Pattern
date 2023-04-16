package behavioural

import org.junit.jupiter.api.Test

/**
 * Mediator Design Pattern :
 * - Provide a central object used for communicating between objects.
 * - Objects don't talk to each other, they talk to mediator
 * - Reduce dependencies between objects.
 */
class ChatUser(private val mediator: Mediator, private val name: String) {

    fun send(message: String) {
        println("$name sending message: $message")
        mediator.sendMessage(message, this)
    }

    fun receive(message: String) {
        println("$name Received message: $message")
    }
}

class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(message: String, user: ChatUser) {
        users.filter { it != user }
            .forEach {
                it.receive(message)
            }
    }

    fun addUser(chatUser: ChatUser): Mediator = apply { users.add(chatUser) }
}

class MediatorTest {

    @Test
    fun testMediator() {
        val mediator = Mediator()
        val suresh = ChatUser(mediator, "Suresh")
        val ramesh = ChatUser(mediator, "Ramesh")
        val jignesh = ChatUser(mediator, "Jignesh")

        mediator.addUser(suresh).addUser(ramesh).addUser(jignesh)
        suresh.send("Hi every one")
    }
}