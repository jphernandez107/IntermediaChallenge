package com.jphernandez.intermediachallenge.characterList

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.repositories.CharacterRepository

class CharacterListVM(private val characterRepository: CharacterRepository): ViewModel() {

    var charactersLiveData: LiveData<PagingData<Character>> = MutableLiveData()

    fun requestCharacters(pageSize: Int = 15) {
        charactersLiveData = characterRepository.getCharacters(pageSize = pageSize)
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