package com.example.interviewsimulator.ui.registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrationScreen()
        }
    }
}

@Composable
fun RegistrationScreen() {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121A21))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Regístrese para comenzar su\npráctica de entrevista en vivo",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextField("Nombre completo", fullName) { fullName = it }
        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField("Número de teléfono", phoneNumber, KeyboardType.Phone) {
            if (it.all { char -> char.isDigit() }) {
                phoneNumber = it
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField("Correo electrónico", email, KeyboardType.Email) {
            email = it
            val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$".toRegex()
            emailError = !emailRegex.matches(email)
        }
        if (emailError) {
            Text(
                text = "Por favor, ingresa un correo electrónico válido.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField("Contraseña", password, KeyboardType.Password, true) { newPassword ->
            password = newPassword
            passwordError = password.length < 6
        }
        if (passwordError) {
            Text(
                text = "La contraseña debe tener al menos 6 caracteres.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!emailError && !passwordError) {
                    // Aquí se puede agregar la lógica de registro
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A80E5)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Registrarse", color = Color.White)
        }
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFF414548)
        ),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                Icon(
                    imageVector = image,
                    contentDescription = "Toggle Password Visibility",
                    tint = Color.White,
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    RegistrationScreen()
}
