package com.example.ppa_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventRegistrationScreen(language: String, onEventRegistered: (Event) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    val context = LocalContext.current

    val title = if (language == "en") "Event Registration" else "Registro de Eventos"
    val nameLabel = if (language == "en") "Name" else "Nombre"
    val descriptionLabel = if (language == "en") "Description" else "Descripción"
    val addressLabel = if (language == "en") "Address" else "Dirección"
    val priceLabel = if (language == "en") "Price" else "Precio"
    val dateLabel = if (language == "en") "Date (dd/MM/yyyy)" else "Fecha (dd/MM/yyyy)"
    val capacityLabel = if (language == "en") "Capacity" else "Aforo"
    val registerButtonText = if (language == "en") "Register Event" else "Registrar Evento"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title, color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            TextField(value = name, onValueChange = { name = it }, label = { Text(nameLabel) })
            TextField(value = description, onValueChange = { description = it }, label = { Text(descriptionLabel) })
            TextField(value = address, onValueChange = { address = it }, label = { Text(addressLabel) })
            TextField(value = price, onValueChange = { price = it }, label = { Text(priceLabel) })
            TextField(value = date, onValueChange = { date = it }, label = { Text(dateLabel) })
            TextField(value = capacity, onValueChange = { capacity = it }, label = { Text(capacityLabel) })
            Button(onClick = {
                try {
                    val priceValue = price.toDouble()
                    val capacityValue = capacity.toInt()
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    dateFormat.isLenient = false
                    dateFormat.parse(date) // This will throw an exception if the date is invalid

                    val event = Event(name, description, address, priceValue, date, capacityValue, "")
                    onEventRegistered(event)
                } catch (e: NumberFormatException) {
                    val errorMessage = if (language == "en") "Please enter valid price and capacity." else "Por favor, introduce un precio y aforo válidos."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                } catch (e: java.text.ParseException) {
                    val errorMessage = if (language == "en") "Please enter a valid date in the format dd/MM/yyyy." else "Por favor, introduce una fecha válida en el formato dd/MM/yyyy."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    val errorMessage = if (language == "en") "Error registering event: ${e.message}" else "Error al registrar el evento: ${e.message}"
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = registerButtonText)
            }
        }
    }
}