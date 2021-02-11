package com.jphernandez.intermediachallenge.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jphernandez.intermediachallenge.data.Character
import io.reactivex.Observable

interface CharacterRepository {

    fun getCharacters(pageSize: Int): LiveData<PagingData<Character>>

    fun getCharacterById(id: Long): Observable<Character>

}