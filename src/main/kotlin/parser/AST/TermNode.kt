package parser.AST

import model.FileTable
import model.TermTable
import org.apache.commons.lang3.ObjectUtils.clone


class TermNode(
    term: String,
    fileIdsByTerm: MutableSet<Int>?,
    files: MutableSet<Int>?,
    fileTable: FileTable,
    termTable: TermTable
) : Expression() {

    private val term: String
    private var fileIdsByTerm: MutableSet<Int>?
    private val fileTable: FileTable
    private val termTable: TermTable
    private val fileIds: MutableSet<Int>?



    init {
        this.term = term
        this.fileTable = fileTable
        this.termTable = termTable
        this.fileIdsByTerm = fileIdsByTerm
        this.fileIds = files
    }

    override fun evaluateBoolean(): MutableSet<Int>?  =  fileIdsByTerm


    override fun evaluateSequence(): MutableSet<Int> {
        val results: MutableSet<Int> = mutableSetOf()
        fileTable.table.forEach { (key, value) -> if (value.contains(term)) results.add(key) }
        return results
    }
}