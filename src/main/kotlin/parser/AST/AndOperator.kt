package parser.AST


class AndOperator(lo: Expression, ro: Expression, fileIds: MutableSet<Int>?) : Expression(fileIds) {
    private val leftOperand: Expression
    private val rightOperand: Expression

    init {
        leftOperand = lo
        rightOperand = ro
    }

    override fun evaluate(): MutableSet<Int>? {
        val leftIds = leftOperand.evaluate()
        val rightIds = rightOperand.evaluate()

        if(leftIds == null) return rightIds
        else if(rightIds == null) return leftIds

        leftIds.retainAll(rightIds)
        return leftIds
    }

}