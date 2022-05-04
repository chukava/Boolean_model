package service

import model.File
import model.Query
import model.TimeDifference
import parser.AST.Expression
import parser.Parser
import preprocessor.Preprocessor



class SearchService(dataSet: String) {
    private val preprocessor: Preprocessor
    private val parser: Parser
    private var query: String = "home"
    private var expression: Expression
    private var time: TimeDifference = TimeDifference(0, 0, 0)

    init {
        println("[INFO] Preprocessing of $dataSet started.")

        preprocessor = Preprocessor("stop_words/stop_words_english")
        preprocessor.preprocess(dataSet) // from resources

        println("[INFO] Preprocessing finished.")

        parser = Parser(preprocessor)
        expression = parser.parse(query)

    }

    fun setQuery(newQuery: Query): Boolean {
        this.query = newQuery.query

        try {
            println("[INFO] Validation of query $query started.")
            expression = parser.parse(this.query)
            println("[INFO] Validation of query finished.")

        } catch (e: Exception) {
            println("[ERROR] ${e.message}")
            return false
        }
        return true
    }

    fun getTimeDiff(): TimeDifference {
        time.diff = time.timeSequence - time.timeBoolean
        return time
    }

    fun booleanSearch(): MutableSet<File> {

        println("[INFO] Evaluation of \"$query\" using inverted indexes started.")

        val begin = System.nanoTime()
        val result = expression.evaluateBoolean()
        val end = System.nanoTime()

        time.timeBoolean = end - begin
        println("[INFO] Evaluation finished with time ${time.timeBoolean}")

        if (result == null) return mutableSetOf()
        return toFile(result)
    }

    fun sequenceSearch(): MutableSet<File> {
        println("[INFO] Evaluation of \"$query\" using sequence search started.")

        val begin = System.nanoTime()
        val result = expression.evaluateSequence()
        val end = System.nanoTime()

        time.timeSequence = end - begin
        println("[INFO] Evaluation finished with time ${time.timeBoolean}")

        if (result == null) return mutableSetOf()
        return toFile(result)
    }

    fun toFile(mutableSet: MutableSet<Int>) : MutableSet<File> {
        val res = mutableSetOf<File>()
        mutableSet.forEach{ fileId -> res.add(File(fileId))}
        return res
    }

}