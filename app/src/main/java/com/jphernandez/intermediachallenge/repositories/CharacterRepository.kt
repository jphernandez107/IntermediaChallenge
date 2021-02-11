package com.jphernandez.intermediachallenge.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jphernandez.intermediachallenge.data.Character
import io.reactivex.Observable

/**
 * Esta clase se encarga de comunicar el ViewModel con el servicio
 */
interface CharacterRepository {

    /**
     * Este metodo se encarga de solicitar al servicio la lista de personajes disponibles.
     * */
    fun getCharacters(pageSize: Int): LiveData<PagingData<Character>>

    /**
     * Este metodo se encarga de solicitar al servicio un personaje a traves de su ID.
     * */
    fun getCharacterById(id: Long): Observable<Character>

}