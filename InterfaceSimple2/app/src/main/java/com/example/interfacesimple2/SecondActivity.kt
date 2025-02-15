package com.example.interfacesimple2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.interfacesimple2.ui.theme.InterfaceSimple2Theme

// Structure de récupération des informations
data class Infos(
    val last_name: String?,
    val first_name: String?,
    val age: String?,
    val domain: String?,
    val phone: String?
)

class SecondActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // récupération des informations
            val infos = Infos(
                last_name = intent.getStringExtra("lastname"),
                first_name = intent.getStringExtra("firstname"),
                age = intent.getStringExtra("age"),
                domain = intent.getStringExtra("domain"),
                phone = intent.getStringExtra("phone")
            )

            InterfaceSimple2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(text = stringResource(R.string.second_title))
                            }
                        )
                    }
                ) { innerPadding ->
                    Information(innerPadding,infos)
                }
            }
        }
    }
}

@Composable
fun Information(innerPadding:PaddingValues,infos:Infos){

    val ctx = LocalContext.current

    Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
        Text(text = stringResource(R.string.last_name) + " " + (infos.last_name?: ""))
        Text(text = stringResource(R.string.first_name)+ " " + (infos.first_name?: ""))
        Text(text = stringResource(R.string.age)+ " " + (infos.age?: ""))
        Text(text = stringResource(R.string.domain)+ " " + (infos.domain?: ""))
        Text(text = stringResource(R.string.phone)+ " " + (infos.phone?: ""))

        // retour à l'activité précédente
        val intent1 = Intent(ctx,MainActivity::class.java).apply{}

        // activité suivante
        val intent2 = Intent(ctx,ThirdActivity::class.java).apply{
            putExtra("lastname",infos.last_name)
            putExtra("firstname",infos.first_name)
            putExtra("age",infos.age)
            putExtra("domain",infos.domain)
            putExtra("phone",infos.phone)
        }

        Button(onClick = {
            ctx.startActivity(intent2)
        }) {
            Text(text = stringResource(R.string.ok_button))
        }
        Button(onClick = {
            ctx.startActivity(intent1)
        }) {
            Text(text = stringResource(R.string.return_button))
        }
    }
}