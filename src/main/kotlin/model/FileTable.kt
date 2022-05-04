package model

class FileTable {
    val table: HashMap<Int, MutableSet<String>> = HashMap()

    fun addFile(fileId: Int) {
        table[fileId] = mutableSetOf()
    }

    fun addTermByFile(fileId: Int, term: String) {
        table[fileId]?.add(term)
    }

    fun getTermsByFile(fileId: Int): MutableSet<String>? = table[fileId]
}