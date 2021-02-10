package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

interface CharacterService {

    fun getCharacters(offset: Int = 0): Observable<QueryResultDto>

    fun getCharacter(id: Long): Observable<QueryResultDto>

}