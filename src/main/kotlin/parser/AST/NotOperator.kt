package parser.AST

import model.File


class NotOperator(op: Expression, fileIds: MutableSet<File>?) : Expression(fileIds) {
    private val operand: Expression

    init {
        operand = op
    }

    override fun evaluateBoolean(): MutableSet<File>? {
        val fileIdsByNotTerm = fileIds
        operand.evaluateBoolean()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }

    override fun evaluateSequence(): MutableSet<File>? {
        val fileIdsByNotTerm = fileIds
        operand.evaluateSequence()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }
}