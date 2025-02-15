package com.example.interfacesimple2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.interfacesimple2.ui.theme.InterfaceSimple2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceSimple2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(text = stringResource(R.string.main_title))
                            }
                        )
                    }
                ){ innerPadding ->
                    Form(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Form(innerPadding: PaddingValues){

    // Variables mutables
    var last_name by remember { mutableStateOf("") }
    var first_name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var domain by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }
    val ctx = LocalContext.current

    Column(Modifier.padding(innerPadding).padding(10.dp)){
        OutlinedTextField(
            label = {
                Text(stringResource(R.string.last_name))
            },
            value = last_name,
            onValueChange = {
                last_name = it
            },
            modifier = Modifier.fillMaxWidth().background(backgroundColor)
        )
        OutlinedTextField(
            label = {
                Text(stringResource(R.string.first_name))
            },
            value = first_name,
            onValueChange = {
                first_name = it
            },
            modifier = Modifier.fillMaxWidth().background(backgroundColor)
        )
        OutlinedTextField(
            label = {
                Text(stringResource(R.string.age))
            },
            value = age,
            onValueChange = {
                age = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().background(backgroundColor)
        )
        OutlinedTextField(
            label = {
                Text(stringResource(R.string.domain))
            },
            value = domain,
            onValueChange = {
                domain = it
            },
            modifier = Modifier.fillMaxWidth().background(backgroundColor)
        )
        OutlinedTextField(
            label = {
                Text(stringResource(R.string.phone))
            },
            value = number,
            onValueChange = {
                number = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().background(backgroundColor)
        )
        Button(onClick = {
            val message = ctx.getString(R.string.welcome_message) + " " + last_name + "!"

            val builder = AlertDialog.Builder(ctx)
            builder.setTitle(R.string.validation_title)
            builder.setMessage(R.string.validation_text)

            // Bouton d'acceptation
            builder.setPositiveButton(R.string.validation_yes){dialog, which ->
                backgroundColor = Color(45,115,217)
                Toast.makeText(
                    ctx,message, Toast.LENGTH_LONG
                ).show()

                val intent = Intent(ctx,SecondActivity::class.java).apply {
                    putExtra("lastname",last_name)
                    putExtra("firstname",first_name)
                    putExtra("age",age)
                    putExtra("domain", domain)
                    putExtra("phone",number)
                }
                ctx.startActivity(intent)
                dialog.dismiss()
            }

            // Bouton de refus
            builder.setNegativeButton(R.string.validation_no){dialog, which ->
                backgroundColor = Color.Transparent
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }) {
            Text(stringResource(R.string.submit_button))
        }


    }
}
