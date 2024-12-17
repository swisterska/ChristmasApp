package com.example.christmasapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GiftHistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_history)

        // Return Button
        val returnButton = findViewById<ImageButton>(R.id.ReturnButton)
        returnButton.setOnClickListener {
            finish() // Go back to the previous screen
        }

        // RecyclerView for Gift History
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(HistoryManager.giftHistory)
        recyclerView.isVerticalScrollBarEnabled = true
    }
}
