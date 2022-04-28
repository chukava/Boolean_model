package parser.AST

import model.File


abstract class Expression(fileIds: MutableSet<File>?) {
    val fileIds : MutableSet<File>?

    init {
        this.fileIds = fileIds
    }

    abstract fun evaluateBoolean(): MutableSet<File>?

    abstract fun evaluateSequence(): MutableSet<File>?
}