package parser.AST

import java.util.*

class NotOperator : Expression(){
    private val expression: Expression? = null

    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}