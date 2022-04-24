import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main() {
    startServer()
}

private fun startServer(){
    val searchService = SearchService()

    val server = embeddedServer(Netty, 8080) {
        routing {
            get("boolean-model/result") {

                call.respond(searchService.booleanSearch().toString())
            }
            get("sequence-search/result") {

                call.respondText("Getting ids", ContentType.Text.Plain)
            }
            post("boolean-model/query") {
                var query = call.receive<String>();
                call.respond("Query accepted")
                searchService.setQuery(query)
            }
        }
    }

    server.start(true)
}






//1. Extrakce a preprocesing termů z dokumentů. - DONE.
//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam). - DONE
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.  (AST, parser) - DONE

//4. Frontend - Javascript (Angular/React?), Bootstrap
 //
//5. Prepare data sets = s, m, l, xl databases.