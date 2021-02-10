package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

class EventServiceImpl(private val retrofit: ServiceRetrofit): EventService {

    override fun getEvents(offset: Int): Observable<QueryResultDto> = retrofit.getEventsList(offset)


}