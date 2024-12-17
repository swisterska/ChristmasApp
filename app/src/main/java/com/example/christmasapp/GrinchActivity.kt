package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageButton

class GrinchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grinch)

        // Retrieve and display the Grinch message
        val grinchMessage = intent.getStringExtra("grinchMessage") ?: "Grinch strikes mysteriously!"
        val grinchMessageText = findViewById<TextView>(R.id.grinchMessageText)
        grinchMessageText.text = grinchMessage

        // Return button logic
        val returnButton = findViewById<ImageButton>(R.id.ReturnButtonWhite)
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
