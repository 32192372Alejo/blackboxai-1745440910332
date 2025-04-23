package com.example.interviewsimulator.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewsimulator.R
import com.example.interviewsimulator.ui.login.LoginActivity
import com.example.interviewsimulator.ui.registration.RegistrationActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        btnIniciarSesion.setOnClickListener {
            try {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir pantalla de inicio de sesión", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistrarse.setOnClickListener {
            try {
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir pantalla de registro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
