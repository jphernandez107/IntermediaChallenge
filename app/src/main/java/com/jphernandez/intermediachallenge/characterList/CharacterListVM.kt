package com.jphernandez.intermediachallenge.characterList

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.repositories.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterListVM(private val characterRepository: CharacterRepository): ViewModel() {

    val charactersLiveData: MutableLiveData<PagingData<Character>> = MutableLiveData()

    fun requestCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.getCharacters(15)
                .cachedIn(viewModelScope)
                .collect { results ->
                    charactersLiveData.postValue(results)
                    Log.d("CharacterListVM Juaann", "The results are: $results")
                }
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