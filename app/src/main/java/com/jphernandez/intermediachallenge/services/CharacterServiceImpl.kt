package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable

class CharacterServiceImpl(private val retrofit: ServiceRetrofit): CharacterService {

    override fun getCharacters(offset: Int): Observable<QueryResultDto> {
        return retrofit.getCharactersList(offset)
    }

    override fun getCharacter(id: Long): Observable<QueryResultDto> = retrofit.getCharacterById(id)

}