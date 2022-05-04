package model


class TermTable {
    private val table: HashMap<String, MutableSet<Int>> = HashMap()

    fun addTermByFile(term: String, fileId: Int) {
        if (table.containsKey(term)) addFileByTerm(term, fileId)
        else addTermToTable(term, fileId)
    }

    private fun addTermToTable(term: String, fileId: Int) {
        table[term] = mutableSetOf(fileId)
    }

    private fun addFileByTerm(term: String, fileId: Int) {
        if (!table[term]!!.contains(fileId)) {
            table[term]!!.add(fileId)
        }
    }

    fun getFilesByTerm(term: String): MutableSet<Int>? = table[term]

    fun printTable() {
        println("[INFO] Printing table of terms.")
        table.forEach { (key, value) ->
            print("$key " + " ".repeat(35 - key.length) + "|      ")
            value.forEach { file -> print("[${file}]") }
            print("\n")
        }
    }

}


