package com.example.interfacesimple2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import com.example.interfacesimple2.ui.theme.InterfaceSimple2Theme
import com.example.interfacesimple2.R

class ThirdActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfaceSimple2Theme {
                CenteredImage()
            }
        }
    }
}

@Composable
fun CenteredImage() {
    // Utilisez un Box pour centrer l'image
    Box(
        modifier = Modifier.fillMaxSize(), // Box occupe tout l'espace disponible
        contentAlignment = Alignment.Center // Centre tout son contenu
    ) {
        Image(
            painter = painterResource(id = R.drawable.john_pork), // Image provenant des ressources
            contentDescription = "Image description", // Description pour l'accessibilité
            modifier = Modifier
                .fillMaxSize(1.0f) // Agrandir l'image à 50% de la taille du conteneur
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InterfaceSimple2Theme {
        CenteredImage()
    }
}