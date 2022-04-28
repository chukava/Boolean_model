package parser.AST

import model.File


class OrOperator(lo: Expression, ro: Expression, fileIds: MutableSet<File>?) : Expression(fileIds) {
    private val leftOperand: Expression
    private val rightOperand: Expression

    init {
        leftOperand = lo
        rightOperand = ro
    }

    override fun evaluateBoolean(): MutableSet<File>? {
        val leftIds = leftOperand.evaluateBoolean()
        val rightIds = rightOperand.evaluateBoolean()

        if (leftIds == null) return rightIds
        else if (rightIds == null) return leftIds

        leftIds.addAll(rightIds)
        return leftIds
    }

    override fun evaluateSequence(): MutableSet<File>? {
        val leftIds = leftOperand.evaluateBoolean()
        val rightIds = rightOperand.evaluateBoolean()

        if (leftIds == null) return rightIds
        else if (rightIds == null) return leftIds

        leftIds.addAll(rightIds)
        return leftIds
    }

}