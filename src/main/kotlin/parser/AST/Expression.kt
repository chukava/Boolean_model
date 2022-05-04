package parser.AST


abstract class Expression {

    abstract fun evaluateBoolean(): MutableSet<Int>?

    abstract fun evaluateSequence(): MutableSet<Int>?
}