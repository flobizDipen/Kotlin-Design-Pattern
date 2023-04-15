package structural

import org.junit.jupiter.api.Test

/**
 * Decorator/ Wrapper Pattern
 * - Attach new behaviour to an object, without altering the existing code.
 * - Override behaviour : Change existing behaviour to provide some functionality.
 */

interface CoffeeMachine {
    fun makeSmallCoffee()

    fun makeMediumCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine : Making small coffee")
    }

    override fun makeMediumCoffee() {
        println("Normal coffee machine : Making medium coffee")
    }
}

// Suppose above methods are provided by third party library, so we can change it but we want to provide some additional functionality over this.

// Decorator
class EnhanceCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    // Overriding behaviour
    override fun makeMediumCoffee() {
        println("Enhanced coffee machine : Making medium coffee")
    }

    // Extended behaviour
    fun makeMilkCoffee() {
        println("Enhanced coffee machine : Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine : Adding milk.")
    }
}

class Decorator {

    @Test
    fun testDecorator() {
        val normalCoffeeMachine = NormalCoffeeMachine()
        val enhanceCoffeeMachine = EnhanceCoffeeMachine(normalCoffeeMachine)

        enhanceCoffeeMachine.makeSmallCoffee()
        println("-----------------------------")
        enhanceCoffeeMachine.makeMediumCoffee()
        println("-----------------------------")
        enhanceCoffeeMachine.makeMilkCoffee()
    }
}