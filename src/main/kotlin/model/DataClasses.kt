package model

@kotlinx.serialization.Serializable
data class File(val fileId : Int)

@kotlinx.serialization.Serializable
data class Query(val query: String)