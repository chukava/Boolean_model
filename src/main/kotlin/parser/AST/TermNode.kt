package parser.AST

import model.File
import model.FileTable
import org.apache.commons.lang3.ObjectUtils.clone


class TermNode(
    term: String,
    fileIdsByTerm: MutableSet<File>?,
    fileIds: MutableSet<File>?,
    fileTable: FileTable
) : Expression(fileIds) {

    private val term: String
    private var fileIdsByTerm: MutableSet<File> = mutableSetOf()
    private val fileTable: FileTable

    init {
        this.term = term
        this.fileTable = fileTable
        if (fileIdsByTerm != null) this.fileIdsByTerm = fileIdsByTerm
    }

    override fun evaluateBoolean(): MutableSet<File> {
        return clone(fileIdsByTerm)
    }

    override fun evaluateSequence(): MutableSet<File> {
        val results: MutableSet<File> = mutableSetOf()
        fileTable.table.forEach { (key, value) -> if (value.contains(term)) results.add(key) }
        return results
    }
}