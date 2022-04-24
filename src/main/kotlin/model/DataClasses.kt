package model

@kotlinx.serialization.Serializable
data class File(val fileId : String)

@kotlinx.serialization.Serializable
data class Query(val query: String)