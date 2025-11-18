package samucafialho.com.github.gsandroidkotlin_3sir.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    usuario: String,
    senha: String
) {
    var usuarioState by remember { mutableStateOf(usuario) }
    var senhaState by remember { mutableStateOf(senha) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFED145B))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "LOGIN",
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = usuarioState,
            onValueChange = { usuarioState = it },
            label = { Text("UsuÃ¡rio") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senhaState,
            onValueChange = { senhaState = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate("menu")
        }) {
            Text("ENTRAR")
        }
    }
}