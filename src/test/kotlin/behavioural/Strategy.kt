package behavioural

import org.junit.jupiter.api.Test
import java.util.*

/**
 * Strategy Design Pattern
 * - A class behaviour or algorithm can be change at runtime.
 * - Objects contains algorithm logic.
 * - Context object that can handle algorithm objects.
 * - Useful when we want to be able to add functionality without changing program structure.
 */

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

val lowerCaseFormatter = { it: String -> it.lowercase(Locale.getDefault()) }
val upperCaseFormatter = { it: String -> it.uppercase(Locale.getDefault()) }

class StrategyTest {

    @Test
    fun testStrategy() {
        val inputString = "LOREM ipsum DOLOR sit amet."

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.printString(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.printString(inputString)
    }
}