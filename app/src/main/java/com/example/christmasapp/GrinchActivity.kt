package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GrinchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grinch)


        val grinchMessage = intent.getStringExtra("grinchMessage") ?: "Grinch strikes mysteriously!"


        val grinchMessageText = findViewById<TextView>(R.id.grinchMessageText)
        grinchMessageText.text = grinchMessage


        val returnButton = findViewById<ImageButton>(R.id.ReturnButtonWhite)
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
