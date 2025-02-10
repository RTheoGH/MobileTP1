package com.example.interfacesimple2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.core.content.ContextCompat
import com.example.interfacesimple2.ui.theme.InterfaceSimple2Theme

class ThirdActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val phone = intent.getStringExtra("phone")

            InterfaceSimple2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(text = stringResource(R.string.third_title))
                            }
                        )
                    }
                ) { innerPadding ->
                    InterfaceAppel(innerPadding,phone!!)
                }
            }
        }
    }
}

@Composable
fun InterfaceAppel(innerPadding:PaddingValues,p:String) {
    val ctx = LocalContext.current
    val perm = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { grant ->
        if(grant){
            appel(ctx,p)
        }
    }

    Column (
        modifier = Modifier.padding(innerPadding).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 8.em,
            text = p,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.telephone),
            contentDescription = "Image de téléphone",
            modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(.9f)
        )
        IconButton(
            modifier = Modifier.size(100.dp),
            colors = IconButtonColors(
                containerColor = Color(23, 128, 61),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            onClick = {
                if (ContextCompat.checkSelfPermission(ctx,android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    appel(ctx,p)
                }else{
                    perm.launch(android.Manifest.permission.CALL_PHONE)
                }

            }) {
            Icon(
                Icons.Rounded.Call,
                contentDescription = "Bouton call",
                modifier = Modifier.size(75.dp)
            )
        }
    }
}

fun appel(ctx:Context, p:String){
    val intentCall = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:$p")
    }

    try {
        ctx.startActivity(intentCall)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}