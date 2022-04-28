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
    val searchService = SearchService()

    routing {
        post("/saveQuery") {
            var query = call.receive<Query>();

            if (searchService.setQuery(query)) call.respond("Query accepted.")
            else call.respond("Bad query syntax.")
        }
        get("/getTimeDifference") {
            call.respond(searchService.getTimeDiff())
        }
        get("boolean-model/getResult") {
            call.respond(searchService.booleanSearch())
        }
        get("sequence-search/getResult") {
            call.respond(searchService.sequenceSearch())
        }
    }
}


//1. Extrakce a preprocesing termů z dokumentů. - DONE.
//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam). - DONE
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.  (AST, parser) - DONE
//4. Sekvencni pruchod DONE

//5. DataSets for testing - s, m , l, xl
//6. Frontend
//7. Time comparing
//8. Documentation



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