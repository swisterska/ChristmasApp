package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

class WelcomePage : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val giftButton = findViewById<ImageButton>(R.id.GiftButton)
        giftButton.setOnClickListener {
            val intent = Intent(this, GiftActivity::class.java)
            startActivity(intent)
        }

        val greetingButton = findViewById<ImageButton>(R.id.GreetingButton)
        greetingButton.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }

        val viewHistoryButton = findViewById<ImageButton>(R.id.ViewHistoryButton)
        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
