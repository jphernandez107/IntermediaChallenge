package com.jphernandez.intermediachallenge.data

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: Thumbnail?,
    val comics: List<Comic>?
)