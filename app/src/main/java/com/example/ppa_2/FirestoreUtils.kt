package com.example.ppa_2

import com.google.firebase.firestore.FirebaseFirestore

fun saveEventToFirestore(event: Event) {
    val db = FirebaseFirestore.getInstance()
    db.collection("events").add(event)
}

fun getEventsFromFirestore(onEventsLoaded: (List<Event>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("events").get().addOnSuccessListener { result ->
        val events = result.map { document -> document.toObject(Event::class.java) }
        onEventsLoaded(events)
    }
}