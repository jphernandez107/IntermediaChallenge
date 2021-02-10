package com.jphernandez.intermediachallenge.characterDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.repositories.CharacterRepository

class CharacterDetailsVM(private val characterRepository: CharacterRepository): ViewModel() {

    val characterLiveData: MutableLiveData<Character> = MutableLiveData()

    fun getCharacterById(characterId: Long) {
        characterRepository.getCharacterById(characterId).subscribe {
            characterLiveData.postValue(it)
        }
    }

}