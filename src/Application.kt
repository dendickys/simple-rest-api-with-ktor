package id.dendickys.api

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.jetty.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient(Jetty) {
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/dicoding/api") {
            val firstName = call.request.queryParameters["firstName"]
            val lastName = call.request.queryParameters["lastName"]

            if(!firstName.isNullOrBlank() && !lastName.isNullOrBlank()) {
                call.respond(mapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "message" to "Hello $firstName $lastName. Welcome to Dicoding!"
                ))
            } else {
                call.respond(mapOf(
                        "message" to "Hello Dicoding Students!"
                    )
                )
            }
        }
    }
}