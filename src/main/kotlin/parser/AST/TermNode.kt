package parser.AST


class TermNode(term: String, fileIds: MutableSet<Int>?) : Expression() {
    private val term: String
    private var fileIds: MutableSet<Int> = mutableSetOf()

    init {
        this.term = term
        if (fileIds != null) this.fileIds = fileIds
    }

    override fun evaluateExpression(): MutableSet<Int>? {
        TODO("Not yet implemented")
    }

}