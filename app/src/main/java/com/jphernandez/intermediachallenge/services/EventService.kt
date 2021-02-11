package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

/**
 * Esta clase se encarga de comunicar el repositorio con el servicio retrofit
 */
interface EventService {

    fun getEvents(offset: Int  = 0): Observable<QueryResultDto>

}