package parser.AST

import java.util.*

abstract class Expression {

    abstract fun evaluateExpression(): MutableSet<Int>?

}