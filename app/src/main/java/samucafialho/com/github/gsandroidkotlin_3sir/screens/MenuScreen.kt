package samucafialho.com.github.gsandroidkotlin_3sir.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MenuScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFED145B)) // vermelho igual IMC
            .padding(32.dp)
    ) {

        // Título Menu
        Text(
            text = "MENU",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // Botões centrais
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {

            // --- BOTÃO IMC ---
            Button(
                onClick = { navController.navigate("imc") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 220.dp, height = 48.dp)
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    color = Color(0xFFED145B),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // --- BOTÃO EQUIPE ---
            Button(
                onClick = { navController.navigate("equipe") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 220.dp, height = 48.dp)
            ) {
                Text(
                    text = "Integrantes",
                    fontSize = 18.sp,
                    color = Color(0xFFED145B),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // --- BOTÃO SAIR ---
            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.size(width = 220.dp, height = 48.dp)
            ) {
                Text(
                    text = "Sair",
                    fontSize = 18.sp,
                    color = Color(0xFFED145B),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
