package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class GiftActivity : AppCompatActivity() {

    private val giftIdeas = arrayOf(
        "LEGO set",
        "Cozy socks",
        "Bluetooth \nheadphones",
        "Gift card",
        "Board game",
        "Scented \ncandles",
        "Plush \nblanket",
        "Fitness \ntracker",
        "Smartphone \nstand",
        "Cookbook",
        "Coffee mug",
        "Portable \nspeaker",
        "Wireless \nearpods",
        "Chocolate \ngift box",
        "Puzzle set",
        "Stationery \nset",
        "Instant \ncamera",
        "Art supplies",
        "Perfume or \ncologne",
        "Wool scarf",
        "Slippers",
        "Backpack",
        "Tool kit",
        "Pocket knife",
        "Electric \ntoothbrush",
        "Portable \ncharger",
        "Succulent \nplant",
        "Jewelry",
        "Luxury pen",
        "Yoga mat",
        "Subscription \nbox",
        "Custom photo \nframe",
        "Cooking \nutensils",
        "Knit hat",
        "Car phone \nmount",
        "Leather \nwallet",
        "Water bottle",
        "Smartwatch",
        "Book set",
        "Gaming \ncontroller",
        "VR headset",
        "Portable \ncoffee maker",
        "Hair styling \ntools",
        "Beanie",
        "Hoodie",
        "Wine glasses",
        "Skincare set",
        "Makeup \npalette",
        "Outdoor \ncamping gear",
        "Travel pillow",
        "Noise-canceling \nheadphones",
        "Mini projector",
        "Robot vacuum",
        "Slime making \nkit",
        "Karaoke \nmicrophone",
        "Telescope",
        "Subscription to \na streaming \nservice",
        "Pet toys",
        "Gourmet snacks \nbasket",
        "Hot chocolate \nset",
        "Bicycle \naccessories",
        "Jigsaw puzzles",
        "Retro video \ngames",
        "Desk organizer",
        "Smart home \ndevice",
        "Bath bomb set",
        "Notebook \njournal",
        "Stylish \nsunglasses",
        "Cooking spices \nset",
        "Custom puzzle \nwith a photo",
        "Personalized \nkeychain",
        "Luxury \nbathrobe",
        "Bean bag \nchair",
        "Digital \npicture frame",
        "DIY craft kit",
        "Musical \ninstrument",

        )

    private val history = mutableListOf<String>()

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
                    // Display random gift after countdown with Grinch logic
                    countdownText.text = "" // Clear the countdown
                    handleGrinchInterference("gift", giftText)
                }
            }
        }
        handler.post(runnable)

        newGiftButton.setOnClickListener {
            handleGrinchInterference("gift", giftText)
        }

        // Return button logic
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }

        // View history button logic
        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleGrinchInterference(type: String, displayText: TextView) {
        if (Random.nextInt(100) < 20) { // 20% chance Grinch interferes
            val grinchMessage = when (type) {

                "gift" -> "Oh no! \nThe Grinch \nstole your present!\n \nGo back to main page."
                else -> "The Grinch strikes again!"
            }
            history.add(grinchMessage)

            // Ensure Intent is created correctly
            val intent = Intent(this, GrinchActivity::class.java)
            intent.putExtra("grinchMessage", grinchMessage)
            startActivity(intent)
        } else {
            val message = when (type) {
                "gift" -> giftIdeas.random()
                else -> "Unexpected choice"
            }
            history.add(message)
            displayText.text = message
        }
    }

}