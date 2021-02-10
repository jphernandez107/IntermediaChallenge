package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

interface EventService {

    fun getEvents(offset: Int  = 0): Observable<QueryResultDto>

}