import parser.Parser
import preprocessor.Preprocessor

class SearchService {
    private var query: String = "home or people"
    private val result: MutableSet<Int>? = mutableSetOf()


    fun setQuery(query: String){
        this.query = query
    }

    fun booleanSearch() : MutableSet<Int>?{
        println("Preprocessing started.")

        val preprocessor = Preprocessor("stop_words_english")
        preprocessor.preprocess("data") // from resources
        println("Preprocessing finished.")

        println("Parsing started.")
        val parser = Parser(preprocessor)

        val expression = parser.parse(query)
        println(expression.evaluate())

        println("Parsing finished.")

        return expression.evaluate()
    }

//    fun sequenceSearch() : MutableSet<Int>? {
//        //
//    }
//
}