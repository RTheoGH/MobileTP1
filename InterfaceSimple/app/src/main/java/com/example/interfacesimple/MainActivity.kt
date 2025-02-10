package com.example.interfacesimple

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

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
