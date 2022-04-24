import model.File
import parser.Parser
import preprocessor.Preprocessor

class SearchService {
    private var query: String = "home or people"
    private val result: MutableSet<Int>? = mutableSetOf()


    fun setQuery(query: String){
        this.query = query
    }

    fun booleanSearch() : MutableList<File>{
        println("Preprocessing started.")

        val preprocessor = Preprocessor("stop_words_english")
        preprocessor.preprocess("data") // from resources
        println("Preprocessing finished.")

        println("Parsing started.")
        val parser = Parser(preprocessor)

        val expression = parser.parse(query)
        val res = expression.evaluate()
        println(res.toString())
        println("Parsing finished.")

        val result : MutableList<File> = mutableListOf()

        if(res != null){
            for (id in res){
                result.add(File(id.toString()))
            }
        }

        return result
    }

//    fun sequenceSearch() : MutableSet<Int>? {
//        //
//    }
//
}