package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

/**
 * Esta clase se encarga de comunicar el repositorio con el servicio retrofit
 */
interface CharacterService {

    /**
     * Este metodo solicita a retrofit la lista de personajes disponibles
     */
    suspend fun getCharacters(offset: Int = 0): QueryResultDto

    /**
     * Este metodo solicita a retrofit un personaje a traves del ID
     */
    fun getCharacter(id: Long): Observable<QueryResultDto>

}