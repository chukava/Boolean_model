package parser.AST


class TermNode(term: String, fileIdsByTerm: MutableSet<Int>?, fileIds: MutableSet<Int>?) : Expression(fileIds) {
    private val term: String
    private var fileIdsByTerm: MutableSet<Int>? = mutableSetOf()

    init {
        this.term = term
        if (fileIdsByTerm != null) this.fileIdsByTerm = fileIdsByTerm
    }

    override fun evaluate(): MutableSet<Int>? {
        return fileIdsByTerm
    }

}