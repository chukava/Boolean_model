package parser.AST


abstract class Expression(fileIds: MutableSet<Int>?) {
    val fileIds : MutableSet<Int>?

    init {
        this.fileIds = fileIds
    }

    abstract fun evaluate(): MutableSet<Int>?
}