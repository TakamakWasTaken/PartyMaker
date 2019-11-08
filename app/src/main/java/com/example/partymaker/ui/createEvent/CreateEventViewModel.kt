package com.example.partymaker.ui.createEvent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateEventViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Create your event"
    }
    val text: LiveData<String> = _text
}