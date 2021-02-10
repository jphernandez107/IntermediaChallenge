package com.jphernandez.intermediachallenge.dto

data class DataDto(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<ResultDto>
)