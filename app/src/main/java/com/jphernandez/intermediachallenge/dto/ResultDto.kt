package com.jphernandez.intermediachallenge.dto

open class ResultDto (
    val id: Long,
    val name: String,
    val title: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: ThumbnailDto,
    val comics: ComicsDto,
    val start: String? = null,
    val end: String? = null
)