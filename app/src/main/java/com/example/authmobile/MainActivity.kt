package com.example.authmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginButton: Button
    lateinit var googleLoginButton: ImageButton
    lateinit var githubLoginButton: ImageButton
    lateinit var forgotPasswordButton: Button
    lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        googleLoginButton = findViewById(R.id.google_login_button)
        githubLoginButton = findViewById(R.id.github_login_button)
        forgotPasswordButton = findViewById(R.id.forgot_password_button)
        registerButton = findViewById(R.id.register_button)

        googleLoginButton.setOnClickListener {
            Toast.makeText(this, "Login com Google", Toast.LENGTH_SHORT).show()
        }

        githubLoginButton.setOnClickListener {
            Toast.makeText(this, "Login com GitHub", Toast.LENGTH_SHORT).show()
        }

        forgotPasswordButton.setOnClickListener {
            Toast.makeText(this, "Esqueceu a senha", Toast.LENGTH_SHORT).show()
        }

        registerButton.setOnClickListener {
            Toast.makeText(this, "Cadastrar", Toast.LENGTH_SHORT).show()
        }
    }
}