package com.jphernandez.intermediachallenge.repositories

import com.jphernandez.intermediachallenge.data.Character
import io.reactivex.Observable

interface CharacterRepository {

    fun getCharacters(offset: Int = 0): Observable<List<Character>>

    fun getCharacterById(id: Long): Observable<Character>

}