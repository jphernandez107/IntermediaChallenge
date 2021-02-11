package com.jphernandez.intermediachallenge.characterList

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.application.IntermediaChallengeApplication
import com.jphernandez.intermediachallenge.characterDetails.CharacterDetailsFragment
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.repositories.CharacterRepository
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CharacterListFragment: Fragment() {

    @Inject
    lateinit var characterRepository: CharacterRepository

    private val viewModel: CharacterListVM by lazy { getViewModel {
        CharacterListVM(characterRepository)
    }}

    private lateinit var adapter: CharactersAdapter
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        IntermediaChallengeApplication.appComponent.inject(this)
        val view = inflater.inflate(R.layout.character_list_fragment, container, false)
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = layoutManager

        adapter = CharactersAdapter { character -> onCharacterClick(character) }
        recyclerView?.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyListTextView = view.findViewById<TextView>(R.id.empty_list_text_view)

        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
                emptyListTextView.visibility = View.GONE
            }
            setLoading(false)
        })

        if(isNetworkConnected()) {
            if(savedInstanceState == null) {
                viewModel.requestCharacters()
                setLoading(true)
            }
        } else {
            Snackbar.make(requireActivity().findViewById(R.id.nav_host_fragment), "No internet connection", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onCharacterClick(character: Character) {
        val bundle = CharacterDetailsFragment.getBundle(character.id, character.name.toUpperCase(Locale.ROOT))
        view?.let { Navigation.findNavController(it).navigate(R.id.action_characterListFragment_to_characterDetailsFragment, bundle) }
    }

    private fun setLoading(loading: Boolean) {
        activity?.findViewById<ProgressBar>(R.id.loading)?.visibility = if(loading) View.VISIBLE else View.GONE
    }

    private fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.activeNetworkInfo?.isConnected == true
    }

    companion object {
        const val PAGE_LIST_SIZE = 15
    }

}