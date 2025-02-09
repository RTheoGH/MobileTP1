package com.example.horairestrains

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.horairestrains.ui.theme.HorairesTrainsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HorairesTrainsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Horaires de trains")
                            }
                        )
                    }
                ){ innerPadding ->
                    Trains(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Trains(innerPadding: PaddingValues) {

    val trains = listOf(
        "08:00 | Lyon - Paris | 12:00",
        "16:00 | Montpellier - Lyon | 18:00",
        "01:25 | Paris - Bordeaux | 04:40",
        "18:15 | Béziers - Toulouse |",
        "09:45 | Montpellier - Marseille | 10:45",
        "23:30 | Paris - Rennes | 00:30",
        "14:15 | Paris - Lyon | 17:00",
        "10:30 | Montpellier - Béziers | 11:00",
        "08:00 | Paris - Bruxelles | 10:00",
        "10:30 | Béziers - Barcelone | 12:00",
        "07:00 | Marseille - Nice | 09:00",
        "12:30 | Lille - Strasbourg | 17:15",
        "06:45 | Lyon - Genève | 08:45",
        "19:20 | Nantes - Paris | 22:00",
        "21:30 | Toulouse - Bordeaux | 23:45",
        "05:15 | Marseille - Lyon | 08:10",
        "15:45 | Rennes - Nantes | 17:00",
        "11:00 | Genève - Lyon | 13:00",
        "08:10 | Bordeaux - Toulouse | 10:30",
        "18:00 | Strasbourg - Mulhouse | 19:30",
        "20:25 | Bruxelles - Paris | 22:30",
        "06:50 | Lyon - Marseille | 09:45",
        "13:00 | Paris - Genève | 16:30",
        "22:15 | Rennes - Brest | 00:30",
        "08:30 | Nantes - Bordeaux | 11:45",
        "10:00 | Lille - Paris | 12:15",
        "17:30 | Marseille - Montpellier | 19:00",
        "05:30 | Paris - Lille | 07:15",
        "16:00 | Bordeaux - Montpellier | 20:15",
        "09:20 | Toulouse - Marseille | 12:45",
        "07:45 | Strasbourg - Lyon | 12:30",
        "14:50 | Paris - Strasbourg | 18:10",
        "12:00 | Barcelone - Montpellier | 14:00",
        "06:30 | Lyon - Turin | 09:15",
        "16:40 | Marseille - Barcelone | 20:00",
        "09:00 | Paris - Amsterdam | 13:15",
        "11:30 | Bruxelles - Amsterdam | 13:45",
        "19:15 | Lille - Bruxelles | 20:30",
        "06:20 | Rennes - Paris | 09:00",
        "08:15 | Nantes - Lyon | 12:30",
        "14:25 | Lyon - Bordeaux | 19:15",
        "05:50 | Montpellier - Béziers | 06:30",
        "07:30 | Marseille - Avignon | 08:45",
        "20:00 | Toulouse - Perpignan | 22:30",
        "18:45 | Marseille - Toulon | 20:00",
        "13:15 | Paris - Marseille | 18:00",
        "08:25 | Montpellier - Perpignan | 10:45",
        "17:10 | Toulouse - Barcelone | 21:30",
        "09:45 | Bordeaux - Lyon | 14:00",
        "15:30 | Genève - Paris | 19:15",
        "11:00 | Nice - Milan | 15:30",
        "10:15 | Lyon - Perpignan | 15:00",
        "12:45 | Nantes - Rennes | 14:15",
        "07:15 | Bordeaux - Biarritz | 09:30",
        "16:45 | Paris - Nice | 23:00"
    )
    var depart by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(listOf<String>()) }

    Column(
        Modifier.padding(innerPadding).padding(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                label = {
                    Text("Départ")
                },
                value = depart,
                onValueChange = {
                    depart = it
                },
                modifier = Modifier.fillMaxWidth(0.4f).padding(2.dp)
            )
            OutlinedTextField(
                label = {
                    Text("Destination")
                },
                value = destination,
                onValueChange = {
                    destination = it
                },
                modifier = Modifier.fillMaxWidth(0.8f).padding(2.dp)
            )
            IconButton(
                modifier = Modifier.padding(2.dp).padding(top = 5.dp),
                colors = IconButtonColors(
                    containerColor = Color(23, 105, 243, 255),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                ),
                onClick = {
                    results = trainsList(trains,depart,destination)
                }
            ) {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            }
        }

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Liste des trains",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(10.dp)
                )
            }
            items(results.size) { result ->
                Text(
                    results[result],
                    Modifier.padding(8.dp)
                )
            }
        }
    }
}

fun trainsList(trains: List<String>, a: String, b: String): List<String> {
    return trains.filter { train ->
        (a.isEmpty() || train.contains("$a -", ignoreCase = true)) &&
                (b.isEmpty() || train.contains("- $b", ignoreCase = true))
    }
}