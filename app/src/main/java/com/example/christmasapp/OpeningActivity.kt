package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class OpeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        val buttonLogin = findViewById<ImageButton>(R.id.ContinueButton)
        buttonLogin.setOnClickListener {

            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
}}