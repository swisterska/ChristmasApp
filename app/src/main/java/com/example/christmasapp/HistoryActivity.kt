package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Button to navigate to Gift History
        val buttonGiftHistory = findViewById<ImageButton>(R.id.GiftHistoryButton)
        buttonGiftHistory.setOnClickListener {
            val intent = Intent(this, GiftHistoryActivity::class.java)
            startActivity(intent)
        }

        // Button to navigate to Greeting History
        val buttonGreetingHistory = findViewById<ImageButton>(R.id.GreetingHistoryButton)
        buttonGreetingHistory.setOnClickListener {
            val intent = Intent(this, GreetingHistoryActivity::class.java)
            startActivity(intent)
        }

        // Return Button
        val returnButton = findViewById<ImageButton>(R.id.ReturnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}
