package com.jphernandez.intermediachallenge.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jphernandez.intermediachallenge.characterList.CharacterPagingSource
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.helpers.convertUniqueCharacter
import com.jphernandez.intermediachallenge.services.CharacterService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CharacterRepositoryImpl(private val characterService: CharacterService): CharacterRepository {

    override fun getCharacters(pageSize: Int): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterPagingSource(characterService) }
        ).liveData
    }

    override fun getCharacterById(id: Long): Observable<Character> =
        characterService.getCharacter(id)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map {
                convertUniqueCharacter(it)
            }

}