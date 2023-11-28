package com.example.appverdaderofalso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AppVerdaderoFalso : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreguntasConNavegacion()
        }
    }
}

@Composable
fun PreguntasConNavegacion() {
    val preguntas = listOf(
        "¿Esta bandera pertenece a la capital de Santa Cruz de Tenerife?",
        "El muñeco de nieve de la imagen sale en la famosa película Frozen",
        "Esta persona que ves en la imagen tiene dinero pa eso",
        "¿Esto es una playa de la isla de Fuerteventura?",
        "La Palma es la mejor isla de Canarias",
        "¿Los carnavales de Tenerife son los mejores?",
        "El CD Tenerife es mejor equipo que el UD Las Palmas",
        "Messi bocadadillo de chorizo Messi bocadillo de chorizo chorizo de teror",
        "En La Palma se dedican a tirar polvos todas las mañanas para desayunar",
        "Nos vamos de romería"
    )

    val respuestasCorrectasEsperadas = listOf(
        true,
        false,
        false,
        true,
        true,
        true,
        false,
        false,
        false,
        true
    )

    val imagenes = listOf(
        R.drawable.santa_cruz_de_tenerife_788x445,
        R.drawable.mu_econieve,
        R.drawable.alhombro,
        R.drawable.playacotufas,
        R.drawable.lostilos,
        R.drawable.carnavales,
        R.drawable.tnflaspalmas,
        R.drawable.bocadillochorizo,
        R.drawable.losindianos,
        R.drawable.romeria
    )

    var indicePregunta by remember { mutableStateOf(0) }
    var respuestasCorrectas by remember { mutableStateOf(0) }
    var respuestasIncorrectas by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (indicePregunta < preguntas.size) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = preguntas[indicePregunta].toUpperCase(),
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    color = Color.Black
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    Image(
                        painter = painterResource(id = imagenes[indicePregunta]),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Agrega espacio entre la imagen y los botones

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .offset(y = (-16).dp) // Ajusta la posición hacia arriba
                    ) {
                        Button(
                            onClick = {
                                val respuesta = respuestasCorrectasEsperadas[indicePregunta]
                                if (respuesta) respuestasCorrectas++ else respuestasIncorrectas++
                                indicePregunta++
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Verdadero")
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp)) // Agrega espacio entre los botones

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .offset(y = (-16).dp) // Ajusta la posición hacia arriba
                    ) {
                        Button(
                            onClick = {
                                val respuesta = !respuestasCorrectasEsperadas[indicePregunta]
                                if (respuesta) respuestasCorrectas++ else respuestasIncorrectas++
                                indicePregunta++
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Falso")
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        indicePregunta = (indicePregunta - 1 + preguntas.size) % preguntas.size
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Anterior",
                            tint = Color.White,
                            modifier = Modifier.padding(end = 8.dp) // Ajusta el espacio entre el ícono y el texto
                        )
                        Text(
                            "PREV", fontSize = 15.sp // Ajusta el tamaño del texto aquí
                        )
                    }
                }
                Button(
                    onClick = {
                        val randomPregunta = (0 until preguntas.size).random()
                        if (randomPregunta != indicePregunta) {
                            indicePregunta = randomPregunta
                        } else {
                            indicePregunta = (indicePregunta + 1) % preguntas.size
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        "RANDOM", fontSize = 15.sp // Ajusta el tamaño del texto aquí
                    )
                }
                Button(
                    onClick = {
                        indicePregunta = (indicePregunta + 1) % preguntas.size
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "NEXT", fontSize = 15.sp // Ajusta el tamaño del texto aquí
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Siguiente",
                            tint = Color.White,
                            modifier = Modifier.padding(end = 8.dp)// Ajusta el espacio entre el texto y el ícono
                        )
                    }
                }
            }
        } else {
            ResultadosScreen(
                respuestasCorrectas = respuestasCorrectas,
                respuestasIncorrectas = respuestasIncorrectas
            )
        }
    }
}

@Composable
fun ResultadosScreen(respuestasCorrectas: Int, respuestasIncorrectas: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resumen", fontSize = 40.sp, color = Color.Black)
        Text(
            "Aciertos: $respuestasCorrectas",
            fontSize = 28.sp,
            color = Color.Green
        )
        Text(
            "Fallos: $respuestasIncorrectas",
            fontSize = 28.sp,
            color = Color.Red
        )
    }
}


@Preview
@Composable
fun PreguntasConNavegacionPreview() {
    PreguntasConNavegacion()
}