package com.example.ppa_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LanguageViewModel : ViewModel() {
    private val _language = MutableStateFlow("es")
    val language: StateFlow<String> = _language

    fun setLanguage(language: String) {
        viewModelScope.launch {
            _language.value = language
        }
    }
}