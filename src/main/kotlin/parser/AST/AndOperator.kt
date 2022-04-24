package parser.AST

import java.util.*

class AndOperator(lo : Expression, ro : Expression)  : Expression() {
    private val leftOperand: Expression
    private val rightOperand: Expression

    init {
        leftOperand = lo
        rightOperand = ro
    }
    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}