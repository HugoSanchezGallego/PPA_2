package com.example.ppa_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ppa_2.ui.theme.PPA_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PPA_2Theme {
                val navController = rememberNavController()
                val languageViewModel: LanguageViewModel = viewModel()
                val language by languageViewModel.language.collectAsState()

                NavHost(navController, startDestination = "language_selection") {
                    composable("language_selection") {
                        LanguageSelectionScreen { selectedLanguage ->
                            languageViewModel.setLanguage(selectedLanguage)
                            navController.navigate("events")
                        }
                    }
                    composable("events") {
                        val events = remember { mutableStateOf(listOf<Event>()) }
                        getEventsFromFirestore { loadedEvents ->
                            events.value = loadedEvents
                        }
                        EventsScreen(events.value, language) {
                            navController.navigate("event_registration")
                        }
                    }
                    composable("event_registration") {
                        EventRegistrationScreen(language) { event ->
                            saveEventToFirestore(event)
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}