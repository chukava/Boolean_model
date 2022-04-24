package parser.AST


class NotOperator(op: Expression, fileIds: MutableSet<Int>?) : Expression(fileIds) {
    private val operand: Expression

    init {
        operand = op
    }

    override fun evaluate(): MutableSet<Int>? {
        var fileIdsByNotTerm = fileIds
        operand.evaluate()?.let { fileIdsByNotTerm?.removeAll(it) }
        return fileIdsByNotTerm
    }

}