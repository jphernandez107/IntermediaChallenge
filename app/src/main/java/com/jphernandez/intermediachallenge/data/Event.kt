package com.jphernandez.intermediachallenge.data

import com.jphernandez.intermediachallenge.dto.ThumbnailDto

data class Event(
             val id: Long,
             val title: String,
             val description: String,
             val modified: String,
             val resourceURI: String,
             val thumbnail: ThumbnailDto,
             val start: String? = null,
             val end: String? = null,
             val comics: List<Comic>?,
             var isExtendedEvent:Boolean = false
)