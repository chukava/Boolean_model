package parser.AST


class AndOperator(lo: Expression, ro: Expression, files: MutableSet<Int>?) : Expression() {
    private val leftOperand: Expression
    private val rightOperand: Expression
    private val fileIds: MutableSet<Int>?

    init {
        leftOperand = lo
        rightOperand = ro
        fileIds = files
    }

    override fun evaluateBoolean(): MutableSet<Int>? {
        val leftIds = leftOperand.evaluateBoolean()
        val rightIds = rightOperand.evaluateBoolean()

        if (leftIds == null) return rightIds
        else if (rightIds == null) return leftIds

        leftIds.retainAll(rightIds)
        return leftIds
    }

    override fun evaluateSequence(): MutableSet<Int>? {
        val leftIds = leftOperand.evaluateSequence()
        val rightIds = rightOperand.evaluateSequence()

        if (leftIds == null) return rightIds
        else if (rightIds == null) return leftIds

        leftIds.retainAll(rightIds)
        return leftIds
    }
}