package model

import java.util.TreeSet

class TermTable {
    private val table: HashMap<String,MutableSet<Int>> = HashMap()
    // TODO : replace List with set to have only unique values

    fun addTermByFile (term: String, fileId: Int) {
        if (table.containsKey(term)) addFileByTerm(term, fileId)
        else addTermToTable(term,fileId)
    }

    private fun addTermToTable (term: String, fileId: Int) {
        table[term] = mutableSetOf(fileId)
    }

    private fun addFileByTerm (term: String, fileId: Int) {
        if (!table[term]!!.contains(fileId)) {
            table[term]!!.add(fileId)
        }
    }

    fun getFilesByTerm (term: String) : Array<Int>? = table[term]?.toTypedArray()

    fun printTable () {
        println("Printing table of terms.")
        for (row in table){
            print(row.key)
            print("   =   ")
            for (a in row.value){
                print(a)
                print(", ")
            }
            println()
        }
    }

}


