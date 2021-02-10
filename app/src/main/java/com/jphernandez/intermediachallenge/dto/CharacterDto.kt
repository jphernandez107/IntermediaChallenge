package com.jphernandez.intermediachallenge.dto

class CharacterDto(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: ThumbnailDto,
    val comics: ComicsDto
)