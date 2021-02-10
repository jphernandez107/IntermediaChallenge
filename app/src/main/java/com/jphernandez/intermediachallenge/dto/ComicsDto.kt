package com.jphernandez.intermediachallenge.dto

data class ComicsDto(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicDto>
)