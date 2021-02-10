package com.jphernandez.intermediachallenge.eventList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jphernandez.intermediachallenge.data.Event
import com.jphernandez.intermediachallenge.repositories.EventRepository

class EventListVM(private val eventRepository: EventRepository): ViewModel() {

    val eventsLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    fun requestEvents() {
        eventRepository.getEvents().subscribe{
            eventsLiveData.postValue(it)
        }
    }
}