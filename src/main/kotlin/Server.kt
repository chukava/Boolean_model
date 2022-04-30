import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.serialization.kotlinx.xml.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Query
import service.SearchService

fun main() {
    startServer()
}

private fun startServer() {
    val server = embeddedServer(Netty, 8080, host = "localhost") {
        configureRouting()
        configureSerialization()
        configureCors()
    }
    server.start(true)
    println("[INFO] Server started.")
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
        xml()
    }
}

fun Application.configureCors() {
    install(CORS) {
        allowCredentials = true
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
}

fun Application.configureRouting() {
    val searchService_S = SearchService("data_S")
    val searchService_L = SearchService("data_L")

    routing {
        post("/saveQuery") {
            val query = call.receive<Query>()

            if (searchService_S.setQuery(query) && searchService_L.setQuery(query)) call.respond("Query accepted.")
            else call.respond("Bad query syntax.")

        }
        get("/getTimeDifference-s") {
            call.respond(searchService_S.getTimeDiff())
        }
        get("boolean-model/getResult-s") {
            call.respond(searchService_S.booleanSearch())
        }
        get("sequence-search/getResult-s") {
            call.respond(searchService_S.sequenceSearch())
        }
        get("/getTimeDifference-l") {
            call.respond(searchService_L.getTimeDiff())
        }
        get("boolean-model/getResult-l") {
            call.respond(searchService_L.booleanSearch())
        }
        get("sequence-search/getResult-l") {
            call.respond(searchService_L.sequenceSearch())
        }
    }
}


//1. Extrakce a preprocesing termů z dokumentů. - DONE.
//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam). - DONE
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.  (AST, parser) - DONE
//4. Sekvencni pruchod DONE
//5. DataSets for testing - s, l - DONE
//7. Time comparing - DONE

//6. Frontend
//8. Documentation
//9. Hosting? - no need!


/* client tests: success
        val client2 = HttpClient(CIO)
        val response1: HttpResponse = client2.post("http://0.0.0.0:8080/boolean-model/query") {
                contentType(ContentType.Application.Json)
                setBody("voter and department")
        }

        println(response1.status)
        println(response1.bodyAsText())



        val client = HttpClient(CIO)
        val response: HttpResponse = client.get("http://0.0.0.0:8080/boolean-model/result")
        println(response.status)
        println(response.bodyAsText())
        client.close()
 */