package com.example.partymaker.ui.listEvents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListEventsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "List of events"
    }
    val text: LiveData<String> = _text
}