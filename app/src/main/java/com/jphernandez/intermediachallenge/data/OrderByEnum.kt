package com.jphernandez.intermediachallenge.data

enum class OrderByEnum(name: String) {
    NAME_ASCENDING("name"),
    NAME_DESCENDING("-name"),
    START_DATE_ASCENDING("-startDate"),
    START_DATE_DESCENDING("startDate"),
    MODIFIED_ASCENDING("modified"),
    MODIFIED_DESCENDING("-modified"),

}