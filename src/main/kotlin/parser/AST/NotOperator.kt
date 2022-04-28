package parser.AST

import model.File


class NotOperator(op: Expression, fileIds: MutableSet<File>?) : Expression(fileIds) {
    private val operand: Expression

    init {
        operand = op
    }

    override fun evaluateBoolean(): MutableSet<File>? {
        var fileIdsByNotTerm = fileIds
        operand.evaluateBoolean()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }

}