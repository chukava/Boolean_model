package model

@kotlinx.serialization.Serializable
data class File(val fileId: Int)

@kotlinx.serialization.Serializable
data class Query(val query: String)

@kotlinx.serialization.Serializable
data class TimeDifference(var timeBoolean: Long, var timeSequence: Long, var diff: Long)