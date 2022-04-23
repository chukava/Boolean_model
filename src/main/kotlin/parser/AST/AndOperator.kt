package parser.AST

import java.util.*

class AndOperator : Expression() {
    private val leftOperand: Expression? = null
    private val rightOperand: Expression? = null

    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}