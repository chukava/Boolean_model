package parser.AST


class NotOperator(op: Expression) : Expression() {
    private val operand: Expression

    init {
        operand = op
    }


    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}