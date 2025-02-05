package com.example.interfacesimple2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val last_name = intent.getStringExtra("lastname")
            val first_name = intent.getStringExtra("firstname")
            val age = intent.getStringExtra("age")
            val domain = intent.getStringExtra("domain")
            val phone = intent.getStringExtra("phone")

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(R.string.last_name) + " " + last_name)
                Text(text = stringResource(R.string.first_name)+ " " + first_name)
                Text(text = stringResource(R.string.age)+ " " + age)
                Text(text = stringResource(R.string.domain)+ " " + domain)
                Text(text = stringResource(R.string.phone)+ " " + phone)

                val intent1 = Intent(this@SecondActivity,MainActivity::class.java).apply{}
                val intent2 = Intent(this@SecondActivity,ThirdActivity::class.java).apply{
                    putExtra("lastname",last_name)
                    putExtra("firstname",first_name)
                    putExtra("age",age)
                    putExtra("domain", domain)
                    putExtra("phone",phone)
                }

                Button(onClick = {
                    startActivity(intent2)
                }) {
                    Text(text = stringResource(R.string.ok_button))
                }
                Button(onClick = {
                    startActivity(intent1)
                }) {
                    Text(text = stringResource(R.string.return_button))
                }
            }

        }
    }
}