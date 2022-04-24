import preprocessor.Preprocessor
import parser.Parser

fun main() {

    println("Preprocessing started.")
    val preprocessor = Preprocessor("stop_words_english")
    preprocessor.preprocess("data") // from resources
    println("Preprocessing finished.")


    println("Parsing started.")
    val parser = Parser(preprocessor)
    val query = "( defeat or department ) and people"

    try {

        val expression = parser.parse(query)
        println(expression.evaluate())

    } catch (e: Exception) {
        println(e.message)
    }
    println("Parsing finished.")

}

//1. Extrakce a preprocesing termů z dokumentů. - DONE.
//2. Efektivní uložení dokumentů v datové struktuře (invertovaný seznam). - DONE
//3. Vyhodnocovací/dotazovací modul využívající strukturu z předchozího kroku.  (AST, parser) - DONE

//4. Frontend - Javascript (Angular/React?), Bootstrap
//5. Hosting ?? no idea so far