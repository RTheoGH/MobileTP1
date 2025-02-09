package com.example.agenda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.agenda.ui.theme.AgendaTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val events = remember { mutableStateListOf<String>() }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK){
                    val data = result.data
                    val date = data?.getStringExtra("date")?:""
                    val nom = data?.getStringExtra("nom")?:""
                    val desc = data?.getStringExtra("desc")?:""

                    if (nom.isNotEmpty() && date.isNotEmpty() && desc.isNotEmpty()){
                        events.add("$date | $nom | $desc")
                        events.sortBy{it.substringBefore(" | ")}
                    }
                }
            }

            AgendaTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Agenda")
                            }
                        )
                    }
                ){ innerPadding ->
                    Agenda(
                        innerPadding,
                        events = events,
                        onAddEvent = {
                            val intent = Intent(this,SecondActivity::class.java)
                            launcher.launch(intent)
                        },
                        onDeleteEvent = { event ->
                            events.remove(event)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun Agenda(innerPadding:PaddingValues,events:List<String>,onAddEvent: () -> Unit,onDeleteEvent: (String) -> Unit) {

    Column(modifier = Modifier.padding(innerPadding).padding(16.dp)){
        IconButton(
            onClick = onAddEvent
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Add Event"
            )
        }

        LazyColumn() {
            item {
                Text(
                    text = "Liste des évènements",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(10.dp)
                )
            }
            items(events) { event ->
                NewEvent(event,onDeleteEvent)
            }
        }
    }
}

@Composable
fun NewEvent(event:String,onDeleteEvent: (String) -> Unit){
    val (date,nom,desc) = event.split(" | ")

    Card(
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = date, style = MaterialTheme.typography.labelLarge)
                Text(text = nom, style = MaterialTheme.typography.titleMedium)
                Text(text = desc, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {onDeleteEvent(event)}
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Supprimer"
                )
            }
        }

    }
}