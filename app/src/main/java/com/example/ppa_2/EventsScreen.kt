package com.example.ppa_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(events: List<Event>, language: String, onAddEventClick: () -> Unit) {
    val title = if (language == "en") "Events" else "Eventos"
    val addButtonText = if (language == "en") "Add Event" else "Añadir Evento"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title, color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddEventClick) {
                Text(text = addButtonText)
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(events) { event ->
                EventItem(event, language)
            }
        }
    }
}

@Composable
fun EventItem(event: Event, language: String) {
    val priceText = if (language == "en") "Price: ${event.price}€" else "Precio: ${event.price}€"

    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberImagePainter(event.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = event.name, fontSize = 20.sp)
            Text(text = event.description)
            Text(text = priceText, fontSize = 12.sp)
        }
    }
}