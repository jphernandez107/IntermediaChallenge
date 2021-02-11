package com.jphernandez.intermediachallenge.repositories

import android.util.Log
import com.jphernandez.intermediachallenge.data.Event
import com.jphernandez.intermediachallenge.helpers.convertEvents
import com.jphernandez.intermediachallenge.services.EventService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class EventRepositoryImpl(private val eventService: EventService): EventRepository {
    override fun getEvents(offset: Int): Observable<List<Event>> =
        eventService.getEvents(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                convertEvents(it)
            }.onErrorReturn {
                Log.e("EventRepositoryImpl", "An error has occurred", it)
                emptyList()
            }

}