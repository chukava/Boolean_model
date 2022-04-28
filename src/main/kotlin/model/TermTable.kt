package model


class TermTable {
    private val table: HashMap<String, MutableSet<File>> = HashMap()

    fun addTermByFile(term: String, fileId: Int) {
        if (table.containsKey(term)) addFileByTerm(term, fileId)
        else addTermToTable(term, fileId)
    }

    private fun addTermToTable(term: String, fileId: Int) {
        table[term] = mutableSetOf(File(fileId))
    }

    private fun addFileByTerm(term: String, fileId: Int) {
        if (!table[term]!!.contains(File(fileId))) {
            table[term]!!.add(File(fileId))
        }
    }

    fun getFilesByTerm(term: String): MutableSet<File>? = table[term]

    fun printTable() {
        println("Printing table of terms.")
        for (row in table) {
            print(row.key)
            print("   =   ")
            for (a in row.value) {
                print(a)
                print(", ")
            }
            println()
        }
    }

}


