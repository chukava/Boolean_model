package preprocessor

import model.TermTable
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import model.File
import model.FileTable
import java.io.BufferedReader
import java.io.FileReader
import java.net.URL
import java.util.*


class Preprocessor(stopWordsFile: String) {
    private val stopWords: List<String>
    private val pipeline: StanfordCoreNLP

    var fileIds : MutableSet<File> = mutableSetOf()
    val termTable = TermTable()
    val fileTable = FileTable()


    init {
        stopWords = initStopWords(stopWordsFile)

        val props = Properties()
        props["annotators"] = "tokenize, ssplit, pos, lemma"
        pipeline = StanfordCoreNLP(props)
    }


    private fun initStopWords(fileName: String): List<String> {
        val list = MutableList(0) { "" }
        var line: String?

        val pathToStopWordsFile: URL? = Preprocessor::class.java.classLoader.getResource("$fileName.txt")
        val file: BufferedReader? = BufferedReader(FileReader(pathToStopWordsFile?.path))

        while (file?.readLine().also { line = it } != null)
            line?.let { list.add(it.trim { it <= ' ' }) }

        return list.toList()
    }


    fun preprocess(folderName: String) {
        var pathToFile: URL?
        var fileId = 1
        while (fileId < 3) {
            try {
                pathToFile = Preprocessor::class.java.classLoader.getResource("$folderName/data$fileId.txt")
                preprocessFile(pathToFile.path, fileId)

                fileIds.add(File(fileId))

            } catch (e: Exception) {
                println("error")
                return
            }
            ++fileId
        }
    }


    private fun preprocessFile(pathToFile: String, fileId: Int) {
        println("Processing file: $pathToFile")

        val file = Annotation(fileToString(pathToFile))
        fileTable.addFile(fileId)
        pipeline.annotate(file)

        val tokens: List<CoreLabel> = file.get<List<CoreLabel>>(TokensAnnotation::class.java)
        for (token in tokens) {
            val word = simplifyTerm(token)
            if (!isAStopWord(word)){
                termTable.addTermByFile(word.lowercase(), fileId)
                fileTable.addTermByFile(fileId, word.lowercase())
            }
        }

        termTable.printTable()
    }


    private fun fileToString(fileName: String): String {
        val str = StringBuilder()
        val file = BufferedReader(FileReader(fileName))
        var line: String?

        while (file.readLine().also { line = it } != null)
            str.append(line)

        return str.toString()
    }


    private fun simplifyTerm(token: CoreLabel): String = token.get(LemmaAnnotation::class.java)


    private fun isAStopWord(word: String): Boolean {
        return if (!word.matches(Regex("[A-Za-z]+"))) true
        else stopWords.contains(word.lowercase())
    }

}



