package model

class FileTable {
    private val table: HashMap<File, MutableSet<String>> = HashMap()

    fun addFile(fileId: Int){
        table.put(File(fileId), mutableSetOf())
    }

    fun addTermByFile(fileId: Int, term: String){
        table[File(fileId)]?.add(term)
    }

    fun getTermsByFile(fileId: Int) : MutableSet<String>? = table[File(fileId)]
}