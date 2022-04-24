package parser.AST


abstract class Expression {

    abstract fun evaluateExpression(): MutableSet<Int>?

}