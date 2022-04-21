package Model

class TermTable {
    private val table: HashMap<String,MutableList<Int>> = HashMap()



    fun addToTable (term: String, fileId: Int) {
        table[term] = MutableList(1) { fileId }
    }

    fun addFileByTerm (term: String, fileId: Int) {
        table[term]?.add(fileId)
    }

    fun getFilesByTerm (term: String) : Array<Int>? = table[term]?.toTypedArray()

}


