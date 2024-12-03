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
fun EventRegistrationScreen(onEventRegistered: (Event) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro de Eventos", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
            TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            TextField(value = address, onValueChange = { address = it }, label = { Text("Dirección") })
            TextField(value = price, onValueChange = { price = it }, label = { Text("Precio") })
            TextField(value = date, onValueChange = { date = it }, label = { Text("Fecha (dd/MM/yyyy)") })
            TextField(value = capacity, onValueChange = { capacity = it }, label = { Text("Aforo") })
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
                    Toast.makeText(context, "Por favor, introduce un precio y aforo válidos.", Toast.LENGTH_LONG).show()
                } catch (e: java.text.ParseException) {
                    Toast.makeText(context, "Por favor, introduce una fecha válida en el formato dd/MM/yyyy.", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al registrar el evento: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "Registrar Evento")
            }
        }
    }
}