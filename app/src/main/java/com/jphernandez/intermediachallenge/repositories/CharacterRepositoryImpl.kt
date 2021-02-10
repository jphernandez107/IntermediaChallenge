package com.jphernandez.intermediachallenge.repositories

import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.helpers.convertCharacters
import com.jphernandez.intermediachallenge.helpers.convertUniqueCharacter
import com.jphernandez.intermediachallenge.services.CharacterService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CharacterRepositoryImpl(private val characterService: CharacterService): CharacterRepository {

    override fun getCharacters(offset: Int): Observable<List<Character>> =
        characterService.getCharacters(offset)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map {
                convertCharacters(it)
            }

    override fun getCharacterById(id: Long): Observable<Character> =
        characterService.getCharacter(id)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map {
                convertUniqueCharacter(it)
            }

}