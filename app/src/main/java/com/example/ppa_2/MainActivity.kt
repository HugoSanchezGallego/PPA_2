package com.example.ppa_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ppa_2.ui.theme.PPA_2Theme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PPA_2Theme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "language_selection") {
                    composable("language_selection") {
                        LanguageSelectionScreen { language ->
                            // Cambiar el idioma de la aplicación
                            navController.navigate("events")
                        }
                    }
                    composable("events") {
                        var events by remember { mutableStateOf(listOf<Event>()) }
                        getEventsFromFirestore { loadedEvents ->
                            events = loadedEvents
                        }
                        EventsScreen(events) {
                            navController.navigate("event_registration")
                        }
                    }
                    composable("event_registration") {
                        EventRegistrationScreen { event ->
                            saveEventToFirestore(event)
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}