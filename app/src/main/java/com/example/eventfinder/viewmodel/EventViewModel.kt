package com.example.eventfinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventfinder.Event
import com.google.firebase.firestore.FirebaseFirestore

class EventViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    init {
        fetchEvents()
    }

    fun fetchEvents() {
        firestore.collection("events").get()
            .addOnSuccessListener { result ->
                val eventList = result.documents.mapNotNull { it.toObject(Event::class.java) }
                _events.value = eventList
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }
}
