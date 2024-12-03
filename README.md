# Prueba Programación Android 2 - Ejercicio 2

# [Repositorio](https://github.com/HugoSanchezGallego/PPA_2.git)

## Índice
1. [Descripción](#descripción)
2. [Requisitos](#requisitos)
3. [Instalación](#instalación)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Uso](#uso)
6. [Pantallas](#pantallas)
    - [Pantalla Principal](#pantalla-principal)
    - [Selección de Idioma](#selección-de-idioma)
    - [Ver Eventos](#ver-eventos)
    - [Registro de Eventos](#registro-de-eventos)
7. [Componentes Reutilizables](#componentes-reutilizables)
8. [Firebase](#firebase)

## Descripción
"PPA_2" es una aplicación móvil desarrollada en Kotlin utilizando Jetpack Compose. Permite a los usuarios gestionar eventos, seleccionando el idioma, visualizando eventos y registrando nuevos eventos.

## Requisitos
- Android Studio Koala Feature Drop | 2024.1.2
- Kotlin 1.9.0
- Firebase Firestore

## Instalación
1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu_usuario/ppa_2.git
    ```
2. Abre el proyecto en Android Studio.
3. Configura Firebase en tu proyecto:
    - Añade el archivo `google-services.json` en el directorio `app/`.
4. Sincroniza el proyecto con Gradle.

## Estructura del Proyecto
- `app/src/main/java/com/example/ppa_2/`
    - `MainActivity.kt`: Actividad principal que configura la navegación.
    - `LanguageSelectionScreen.kt`: Pantalla para seleccionar el idioma.
    - `EventsScreen.kt`: Pantalla para ver los eventos.
    - `EventRegistrationScreen.kt`: Pantalla para registrar un evento.
    - `LanguageViewModel.kt`: ViewModel para gestionar el estado del idioma.
    - `Event.kt`: Modelo de datos para los eventos.
    - `FirestoreUtils.kt`: Utilidades para interactuar con Firebase Firestore.

## Uso
1. Ejecuta la aplicación en un dispositivo o emulador Android.
2. Navega entre las pantallas utilizando los botones disponibles.

## Pantallas

### Pantalla Principal
- **Descripción**: Pantalla inicial con opciones para seleccionar el idioma y navegar a la lista de eventos.
- **Componentes**: Botones de navegación.

### Selección de Idioma
- **Descripción**: Permite seleccionar el idioma de la aplicación (inglés o español).
- **Componentes**: Botones para seleccionar el idioma.

### Ver Eventos
- **Descripción**: Muestra la lista de eventos disponibles.
- **Componentes**: Lista de eventos, botón para añadir un nuevo evento.

### Registro de Eventos
- **Descripción**: Permite registrar un nuevo evento.
- **Validaciones**:
    - Todos los campos deben estar llenos.
    - El formato de la fecha debe ser `dd/MM/yyyy`.
    - El precio y la capacidad deben ser valores numéricos válidos.
- **Componentes**: Campos de texto, botón de acción.

## Componentes Reutilizables

### Header
- **Descripción**: Componente de encabezado con un fondo azul cian y texto negro.
- **Uso**: Incluido en todas las pantallas para mostrar el título correspondiente.

## Firebase
- **Configuración**: Utiliza Firebase Firestore para almacenar y recuperar datos de los eventos.
- **Dependencias**: 
    ```kotlin
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    ```
