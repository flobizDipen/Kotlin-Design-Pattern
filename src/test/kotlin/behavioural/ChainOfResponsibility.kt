package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Chain of Responsibility
 * - Define a chains of handlers to process the request.
 * - Each handler contains a reference of a next handler.
 * - Each handler decides to process the request AND/OR pass it on.
 * - Request can be of the different types
 *
 *
 * Example :
 *                            Constraint Layout
 *                              |             |
 *                             |               |
 *                        Linear Layout   Relative Layout
 *                           |     |         |       |
 *                          |       |       |         |
 *                      Button   Textview  Button   Textview
 */

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(private val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\nAuthentication: $token".let {
        next?.addHeader(it) ?: it
    }
}

class ContentTypeHeader(private val contentType: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\nContentType: $contentType".let {
        next?.addHeader(it) ?: it
    }
}

class BodyPayloadHeader(private val body: String?, val next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String) = "$inputHeader\n$body".let {
        next?.addHeader(it) ?: it
    }
}

class ChainOfResponsibilityTest {

    @Test
    fun testCOR() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("Body: {\"username\" = \"Dipen\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Headers with Authentication")
        println(messageWithAuthentication)

        println("--------------------------")
        val messageWithoutAuthentication = contentTypeHeader.addHeader("Headers without Authentication")
        println(messageWithoutAuthentication)

        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with Authentication
                Authentication: 123456
                ContentType: json
                Body: {"username" = "Dipen"}
            """.trimIndent()
        )

        Assertions.assertThat(messageWithoutAuthentication).isEqualTo(
            """
                Headers without Authentication
                ContentType: json
                Body: {"username" = "Dipen"}
            """.trimIndent()
        )
    }
}