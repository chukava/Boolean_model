import io.ktor.client.*
import io.ktor.client.engine.cio.*
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
                call.respondText("Not implemented yet", ContentType.Text.Plain)
            }
            post("boolean-model/query") {
                var query = call.receive<String>();
                println(query)
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