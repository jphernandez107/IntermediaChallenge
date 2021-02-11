package com.jphernandez.intermediachallenge.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jphernandez.intermediachallenge.data.Character
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(pageSize: Int): Flow<PagingData<Character>>

    fun getCharacterById(id: Long): Observable<Character>

}