package com.jphernandez.intermediachallenge.characterDetails

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.activities.MainActivity
import com.jphernandez.intermediachallenge.application.IntermediaChallengeApplication
import com.jphernandez.intermediachallenge.characterList.getViewModel
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.helpers.displayFullImage
import com.jphernandez.intermediachallenge.repositories.CharacterRepository
import javax.inject.Inject

class CharacterDetailsFragment: Fragment() {

    @Inject
    lateinit var characterRepository: CharacterRepository

    private var recyclerView: RecyclerView? = null
    private lateinit var comicsAdapter: CharacterComicsAdapter
    private val viewModel: CharacterDetailsVM by lazy { getViewModel{ CharacterDetailsVM(characterRepository) } }

    private var characterId: Long? = null

    private lateinit var characterImage: ImageView
    private lateinit var characterDescription: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        IntermediaChallengeApplication.appComponent.inject(this)
        val view = inflater.inflate(R.layout.character_detail_fragment, container, false)
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = layoutManager

        comicsAdapter = CharacterComicsAdapter()
        recyclerView?.adapter = comicsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterId = arguments?.getLong(CHARACTER_ID)

        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            loadView(it, view)
        })

        characterId?.let {
            if(isNetworkConnected()) {
                setLoading(true)
                viewModel.getCharacterById(it)
            } else {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadView(character: Character, view: View) {
        characterImage = view.findViewById(R.id.character_image)
        characterDescription = view.findViewById(R.id.character_description)

        if(character.description.isEmpty()) {
            characterDescription.visibility = View.GONE
        } else {
            characterDescription.visibility = View.VISIBLE
            characterDescription.text = character.description
        }
        displayFullImage(character.thumbnail.path, character.thumbnail.extension, characterImage)

        comicsAdapter.submitList(character.comics)
        setLoading(false)
        if (activity is MainActivity) (activity as MainActivity).title = "Thanos"
    }

    private fun setLoading(loading: Boolean) {
        activity?.findViewById<ProgressBar>(R.id.loading)?.visibility = if(loading) View.VISIBLE else View.GONE
    }

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_NAME = "CHARACTER_NAME"

        fun getBundle(characterId: Long, characterName: String): Bundle {
            val bundle = Bundle()
            bundle.putLong(CHARACTER_ID, characterId)
            bundle.putString(CHARACTER_NAME, characterName)
            return bundle
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.activeNetworkInfo?.isConnected == true
    }

}