package dk.w4

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import freemarker.cache.*
import io.ktor.freemarker.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import java.time.*
import kotlin.test.*
import io.ktor.server.testing.*

class GameSetupTest {
    @Test
    fun pageLoadTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/gamesetup").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                //assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
