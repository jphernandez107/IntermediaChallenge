package com.jphernandez.intermediachallenge.characterList

import androidx.paging.PagingSource
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.helpers.convertCharacters
import com.jphernandez.intermediachallenge.services.CharacterService
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(private val characterService: CharacterService): PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: 0
        return try {
            val response = characterService.getCharacters(position)
            val characters = convertCharacters(response)
            LoadResult.Page(
                data = characters,
                prevKey = if (position == 0) null else position,
                nextKey = if (characters.isEmpty()) null else position + CharacterListFragment.PAGE_LIST_SIZE
            )
        } catch (e:IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}