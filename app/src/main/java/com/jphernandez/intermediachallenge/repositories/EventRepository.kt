package com.jphernandez.intermediachallenge.repositories

import com.jphernandez.intermediachallenge.data.Event
import io.reactivex.Observable

interface EventRepository {

    fun getEvents(offset: Int = 0): Observable<List<Event>>

}