package com.jphernandez.intermediachallenge.eventList

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.application.IntermediaChallengeApplication
import com.jphernandez.intermediachallenge.characterList.getViewModel
import com.jphernandez.intermediachallenge.repositories.EventRepository
import javax.inject.Inject

class EventListFragment: Fragment() {

    @Inject
    lateinit var eventRepository: EventRepository

    private val viewModel: EventListVM by lazy { getViewModel {
        EventListVM(eventRepository)
    }}

    private lateinit var adapter: EventsAdapter
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        IntermediaChallengeApplication.appComponent.inject(this)
        val view = inflater.inflate(R.layout.event_list_fragment, container, false)
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = layoutManager

        adapter = EventsAdapter()
        recyclerView?.adapter = adapter

        if(isNetworkConnected()) {
            setLoading(true)
            viewModel.requestEvents()
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyListTextView = view.findViewById<TextView>(R.id.empty_list_text_view)

        viewModel.eventsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                adapter.submitList(mutableListOf())
                emptyListTextView.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
                emptyListTextView.visibility = View.GONE
            }
            setLoading(false)
        })
    }

    private fun setLoading(loading: Boolean) {
        activity?.findViewById<ProgressBar>(R.id.loading)?.visibility = if(loading) View.VISIBLE else View.GONE
    }

    private fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.activeNetworkInfo?.isConnected == true
    }

}