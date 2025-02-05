package com.example.tp2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp2.ui.theme.AgeCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "greeting") {
        composable("greeting") { GreetingScreen(navController) }
        composable("second/{userName}/{age}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Utilisateur inconnu"
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: -1
            SecondScreen(userName, age)
        }
    }
}


@Composable
fun GreetingScreen(navController: NavHostController , modifier: Modifier = Modifier) {
    var textNameValue by remember { mutableStateOf("") }
    var textAnneeValue by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenue",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(250.dp))

        Text(
            text = textNameValue,
            fontSize = 20.sp
        )

        TextFieldName(userName = textNameValue, onUserNameChange = { textNameValue = it })

        Spacer(modifier = Modifier.height(10.dp))

        TextFieldAge(age = textAnneeValue, onAgeUserChange = { textAnneeValue = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val age = AgeCalculator.calculerAge(textAnneeValue)

                if (textNameValue.isNotEmpty() && age != -1) {
                    navController.navigate("second/$textNameValue/$age")
                } else {
                    Toast.makeText(context, "Veuillez entrer un nom et une année valide", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Valider")
        }
    }
}


@Composable
fun TextFieldName(userName: String, onUserNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = userName,
        onValueChange = onUserNameChange,
        label = { Text("Saisir votre nom") },

    )
}

@Composable
fun TextFieldAge(age: String, onAgeUserChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = age,
        onValueChange = onAgeUserChange,
        label = { Text("Saisir votre année de naissance") },
    )
}


@Composable
fun SecondScreen(userName: String, age: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $userName, vous avez $age ans!",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

