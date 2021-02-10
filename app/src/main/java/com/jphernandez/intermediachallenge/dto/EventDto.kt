package com.jphernandez.intermediachallenge.dto

class EventDto(
    val id: Long,
    val title: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: ThumbnailDto,
    val start: String? = null,
    val end: String? = null
)