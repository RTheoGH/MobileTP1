package com.example.interfacesimple

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interfacesimple.ui.theme.InterfaceSimpleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nomET = findViewById<EditText>(R.id.nomEditText)
        val prenomET = findViewById<EditText>(R.id.prenomEditText)
        val ageET = findViewById<EditText>(R.id.ageEditText)
        val domaineET = findViewById<EditText>(R.id.domaineEditText)
        val phoneET = findViewById<EditText>(R.id.phoneEditText)
        val valider = findViewById<Button>(R.id.validerButton)

        valider.setOnClickListener{
            val nom = nomET.text.toString()
            val prenom = prenomET.text.toString()
            val age = ageET.text.toString()
            val domaine = domaineET.text.toString()
            val phone = phoneET.text.toString()

            val message = """
                Nom : $nom
                Prénom : $prenom
                Âge : $age
                Domaine : $domaine
                Téléphone : $phone
            """.trimIndent()

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
