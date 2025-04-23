package com.example.interviewsimulator.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewsimulator.R

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    emailError = email.text.isNotEmpty() && !emailPattern.matches(email.text)
    passwordError = password.text.isNotEmpty() && password.text.length < 6
    val isFormValid = !emailError && !passwordError && email.text.isNotEmpty() && password.text.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121A21))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { /* Aquí se puede implementar la navegación hacia atrás */ },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Iniciar sesión",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Correo electrónico", color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = emailError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    errorBorderColor = Color.Red,
                    errorCursorColor = Color.Red,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    errorLabelColor = Color.Red,
                    containerColor = Color(0xFF414548)
                )
            )
            if (emailError) {
                Text(
                    text = "Por favor ingresa un correo electrónico válido",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Contraseña", color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                textStyle = TextStyle(color = Color.White),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = passwordError,
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle Password Visibility", tint = Color.White)
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    errorBorderColor = Color.Red,
                    errorCursorColor = Color.Red,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    errorLabelColor = Color.Red,
                    containerColor = Color(0xFF414548)
                )
            )
            if (passwordError) {
                Text(
                    text = "La contraseña debe tener al menos 6 caracteres",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.clickable { /* Acción para recuperar contraseña */ }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Acción de inicio de sesión */ },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A80E5)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Iniciar sesión", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Navegar a registro */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64C351)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Registrarse", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "O iniciar sesión con",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google", modifier = Modifier.size(32.dp))
                Image(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = "Facebook", modifier = Modifier.size(32.dp))
                Image(painter = painterResource(id = R.drawable.ic_linkedin), contentDescription = "LinkedIn", modifier = Modifier.size(32.dp))
            }
        }
    }
}
