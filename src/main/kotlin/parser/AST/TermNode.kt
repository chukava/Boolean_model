package parser.AST

import model.File


class TermNode(term: String, fileIdsByTerm: MutableSet<File>?, fileIds: MutableSet<File>?) : Expression(fileIds) {
    private val term: String
    private var fileIdsByTerm: MutableSet<File>? = mutableSetOf()


    init {
        this.term = term
        if (fileIdsByTerm != null) this.fileIdsByTerm = fileIdsByTerm
    }

    override fun evaluateBoolean(): MutableSet<File>? {
        return fileIdsByTerm
    }

}