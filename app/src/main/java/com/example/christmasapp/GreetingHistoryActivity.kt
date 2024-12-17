package com.example.christmasapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GreetingHistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting_history)

        // Return Button
        val returnButton = findViewById<ImageButton>(R.id.ReturnButton)
        returnButton.setOnClickListener {
            finish() // Go back to the previous screen
        }

        // RecyclerView for Greeting History
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(HistoryManager.greetingHistory)
        recyclerView.isVerticalScrollBarEnabled = true
    }
}
