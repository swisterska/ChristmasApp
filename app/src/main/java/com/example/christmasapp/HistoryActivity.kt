package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

class HistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val buttonGiftHistory = findViewById<ImageButton>(R.id.GiftHistoryButton)
        buttonGiftHistory.setOnClickListener {
            val intent = Intent(this, GiftHistoryActivity::class.java)
            startActivity(intent)
        }

        val buttonGreetingHistory = findViewById<ImageButton>(R.id.GreetingHistoryButton)
        buttonGreetingHistory.setOnClickListener {
            val intent = Intent(this, GreetingHistoryActivity::class.java)
            startActivity(intent)
        }

        val returnButton = findViewById<ImageButton>(R.id.ReturnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}
