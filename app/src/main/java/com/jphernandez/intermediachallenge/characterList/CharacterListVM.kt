package com.jphernandez.intermediachallenge.characterList

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.repositories.CharacterRepository

class CharacterListVM(private val characterRepository: CharacterRepository): ViewModel() {

    val charactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()

    fun requestCharacters() {
        characterRepository.getCharacters().subscribe{
            charactersLiveData.postValue(it)
        }
    }
}



inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this,
            ViewModelFactory(creator)
        ).get(T::class.java)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

}