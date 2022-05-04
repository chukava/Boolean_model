package parser.AST


class NotOperator(op: Expression, files: MutableSet<Int>?) : Expression() {
    private val operand: Expression
    private val fileIds: MutableSet<Int>?

    init {
        operand = op
        fileIds = files
    }

    override fun evaluateBoolean(): MutableSet<Int>? {
        val fileIdsByNotTerm = fileIds
        operand.evaluateBoolean()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }

    override fun evaluateSequence(): MutableSet<Int>? {
        val fileIdsByNotTerm = fileIds
        operand.evaluateSequence()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }
}