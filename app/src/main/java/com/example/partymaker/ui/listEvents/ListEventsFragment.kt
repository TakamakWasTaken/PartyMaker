package com.example.partymaker.ui.listEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.partymaker.R

class ListEventsFragment : Fragment() {

    private lateinit var listEventsViewModel: ListEventsViewModel

    //adding  view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listEventsViewModel =
            ViewModelProviders.of(this).get(ListEventsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list_events, container, false)
        val textView: TextView = root.findViewById(R.id.text_list_events)
        listEventsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}