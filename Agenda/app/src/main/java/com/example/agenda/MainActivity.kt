package com.example.agenda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.agenda.ui.theme.AgendaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val date = intent?.getStringExtra("date")?:""
        val nom = intent?.getStringExtra("nom")?:""
        val desc = intent?.getStringExtra("desc")?:""
        enableEdgeToEdge()
        setContent {
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
                    Agenda(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Agenda(innerPadding:PaddingValues) {

    val ctx = LocalContext.current

    Column(modifier = Modifier.padding(innerPadding).padding(16.dp)){
        IconButton(
            onClick = {
                val intent = Intent(ctx,SecondActivity::class.java)
                ctx.startActivity(intent)
            }
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Add Event"
            )
        }

        LazyColumn() {

        }
    }
}


