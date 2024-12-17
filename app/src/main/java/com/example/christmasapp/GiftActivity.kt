package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class GiftActivity : BaseActivity() {


    private val giftIdeas = arrayOf(

        "Subscription to a streaming service",
        "LEGO set",
        "Cozy socks",
        "Bluetooth headphones",
        "Gift card",
        "Board game",
        "Scented candles",
        "Plush blanket",
        "Fitness tracker",
        "Smartphone stand",
        "Cookbook",
        "Coffee mug",
        "Portable speaker",
        "Wireless earpods",
        "Chocolate gift box",
        "Puzzle set",
        "Stationery set",
        "Instant camera",
        "Art supplies",
        "Perfume or cologne",
        "Wool scarf",
        "Slippers",
        "Backpack",
        "Tool kit",
        "Pocket knife",
        "Electric toothbrush",
        "Portable charger",
        "Succulent plant",
        "Jewelry",
        "Luxury pen",
        "Yoga mat",
        "Subscription box",
        "Custom photo frame",
        "Cooking utensils",
        "Knit hat",
        "Car phone mount",
        "Leather wallet",
        "Water bottle",
        "Smartwatch",
        "Book set",
        "Gaming controller",
        "VR headset",
        "Portable coffee maker",
        "Hair styling tools",
        "Beanie",
        "Hoodie",
        "Wine glasses",
        "Skincare set",
        "Makeup palette",
        "Outdoor camping gear",
        "Travel pillow",
        "Noise-canceling headphones",
        "Mini projector",
        "Robot vacuum",
        "Slime making kit",
        "Karaoke microphone",
        "Telescope",
        "Pet toys",
        "Gourmet snacks basket",
        "Hot chocolate set",
        "Bicycle accessories",
        "Jigsaw puzzles",
        "Retro video games",
        "Desk organizer",
        "Smart home device",
        "Bath bomb set",
        "Notebook journal",
        "Stylish sunglasses",
        "Cooking spices set",
        "Custom puzzle with a photo",
        "Personalized keychain",
        "Luxury bathrobe",
        "Bean bag chair",
        "Digital picture frame",
        "DIY craft kit",
        "Musical instrument",



        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift)

        val countdownText = findViewById<TextView>(R.id.CountdownText)
        val giftText = findViewById<TextView>(R.id.GiftText)
        val returnButton = findViewById<ImageButton>(R.id.ReturnButton)
        val viewHistoryButton = findViewById<ImageButton>(R.id.ViewHistoryButton)
        val newGiftButton = findViewById<ImageButton>(R.id.NewGiftButton)

        val countdownNumbers = arrayOf("3", "2", "1")
        var currentIndex = 0

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (currentIndex < countdownNumbers.size) {
                    countdownText.text = countdownNumbers[currentIndex]
                    currentIndex++
                    handler.postDelayed(this, 1000) // 1 second delay
                } else {
                    countdownText.text = "" // Clear the countdown
                    handleGrinchInterference("gift", giftText)
                }
            }
        }
        handler.post(runnable)

        newGiftButton.setOnClickListener {
            handleGrinchInterference("gift", giftText)
        }

        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }

        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, GiftHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleGrinchInterference(type: String, displayText: TextView) {
        if (Random.nextInt(100) < 20) { // 20% chance Grinch interferes
            val grinchMessage = when (type) {
                "gift" -> "Oh no! \nThe Grinch \nstole your present!\n \nGo back to main page."
                else -> "The Grinch strikes again!"
            }
            // Log Grinch interference to history
            HistoryManager.giftHistory.add("💔Oh no! The Grinch stole your present!")

            // Ensure Intent is created correctly
            val intent = Intent(this, GrinchActivity::class.java)
            intent.putExtra("grinchMessage", grinchMessage)
            startActivity(intent)
        } else {
            val gift = giftIdeas.random()
            displayText.text = gift
            // Add successful gift to history
            HistoryManager.giftHistory.add("🎁 $gift")
        }
    }}
