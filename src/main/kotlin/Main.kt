import preprocessor.Preprocessor
import parser.Parser

fun main(args: Array<String>) {

    println("Preprocessing started.")
    val preprocessor: Preprocessor = Preprocessor("stop_words_english")
    preprocessor.preprocess("data") // from resources
    println("Preprocessing finished.")


    println("Parsing started.")
    val parser: Parser = Parser()
    val query: String = "home"
    try {
        val exp = parser.parse(query)
    } catch (e: Exception) {
        println(e.message)
    }
    println("Parsing finished.")

}

//1. Extrakce a preprocesing termů z dokumentů. - DONE.
//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam). - DONE


//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.  (AST, parser)
//4. Frontend - Javascript (Angular/React?), Bootstrap
//5. Hosting ?? no idea so far