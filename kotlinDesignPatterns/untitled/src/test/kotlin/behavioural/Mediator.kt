package behavioural

import org.junit.jupiter.api.Test

class ChatUser(private val mediator: Mediator, private val name: String) {
    fun send(message: String) {
        println("$name: Sending message: $message")
        mediator.sendMessage(message, this)
    }

    fun receive(message: String) {
        println("$name: Received message: $message")
    }
}

class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(message: String, user: ChatUser) {
        users
            .filter { it != user }
            .forEach { it.receive(message) }
    }

    fun addUser(user: ChatUser): Mediator = apply { users.add(user) }
}

class MediatorTest {
    @Test
    fun testMediator() {
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Ali")
        val bob = ChatUser(mediator, "Mamad")
        val carol = ChatUser(mediator, "Zahra")

        mediator.addUser(alice)
            .addUser(bob)
            .addUser(carol)

        carol.send("Hi everyone!")
    }
}