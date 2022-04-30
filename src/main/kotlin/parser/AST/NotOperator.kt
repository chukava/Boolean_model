package parser.AST

import model.File
import org.apache.commons.lang3.ObjectUtils.clone


class NotOperator(op: Expression, fileIds: MutableSet<File>?) : Expression(fileIds) {
    private val operand: Expression

    init {
        operand = op
    }

    override fun evaluateBoolean(): MutableSet<File>? {
        val fileIdsByNotTerm = clone(fileIds)
        operand.evaluateBoolean()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }

    override fun evaluateSequence(): MutableSet<File>? {
        val fileIdsByNotTerm = clone(fileIds?.toMutableSet())
        operand.evaluateSequence()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }
}