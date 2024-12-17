package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

class OpeningActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        // Continue button logic
        val buttonLogin = findViewById<ImageButton>(R.id.ContinueButton)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}
