package samucafialho.com.github.gsandroidkotlin_3sir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import samucafialho.com.github.gsandroidkotlin_3sir.screens.IntegrantesScreen
import samucafialho.com.github.gsandroidkotlin_3sir.screens.LoginScreen
import samucafialho.com.github.gsandroidkotlin_3sir.screens.MenuScreen
import samucafialho.com.github.gsandroidkotlin_3sir.screens.calcularImc
import samucafialho.com.github.gsandroidkotlin_3sir.screens.determinarClassificacaoIMC
import samucafialho.com.github.gsandroidkotlin_3sir.ui.theme.GSAndroidKotlin3SIRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GSAndroidKotlin3SIRTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"        // <- Igual ao professor
                    ) {

                        // LOGIN NORMAL (sem argumentos)
                        composable("login") {
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                usuario = "",
                                senha = ""
                            )
                        }

                        // LOGIN COM ARGUMENTOS (igual PerfilScreen do professor)
                        composable(
                            route = "login/{usuario}/{senha}",
                            arguments = listOf(
                                navArgument("usuario") { type = NavType.StringType },
                                navArgument("senha") { type = NavType.StringType }
                            )
                        ) {
                            val usuario = it.arguments?.getString("usuario", "usuÃ¡rio")
                            val senha = it.arguments?.getString("senha", "senha")

                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                usuario!!,
                                senha!!
                            )
                        }

                        composable("menu") {
                            MenuScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController
                            )
                        }

                        composable("integrantes") {
                            IntegrantesScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController
                            )
                        }

                        composable("imc") {
                            IMCScreen(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun IMCScreen(modifier: Modifier = Modifier) {

    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var statusImc by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(colorResource(id = R.color.vermelho_fiap))
            ) {
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xfff9f6f6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {

                        Text(
                            text = "Suas informaÃ§Ãµes",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.vermelho_fiap),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Seu peso",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.vermelho_fiap),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        OutlinedTextField(
                            value = peso,
                            onValueChange = { peso = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Seu peso em Kg") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Sua altura",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.vermelho_fiap),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        OutlinedTextField(
                            value = altura,
                            onValueChange = { altura = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Sua altura em cm") },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                imc = calcularImc(
                                    altura = altura.toDouble(),
                                    peso = peso.toDouble()
                                )
                                statusImc = determinarClassificacaoIMC(imc)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.vermelho_fiap)
                            )
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = "Resultado",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = statusImc,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }

                Text(
                    text = String.format("%.1f", imc),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}