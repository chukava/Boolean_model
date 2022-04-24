package parser.AST

import java.util.*

class TermNode(term: String) : Expression() {
    private val term: String

    init {
        this.term = term
    }

    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}