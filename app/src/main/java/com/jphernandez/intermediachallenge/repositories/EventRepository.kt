package com.jphernandez.intermediachallenge.repositories

import com.jphernandez.intermediachallenge.data.Event
import io.reactivex.Observable

/**
 * Esta clase se encarga de comunicar el ViewModel con el servicio
 */
interface EventRepository {

    /**
     * Este metodo se encarga de solicitar al servicio la lista de eventos
     * */
    fun getEvents(offset: Int = 0): Observable<List<Event>>

}