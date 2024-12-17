package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.media.MediaPlayer


class GreetingActivity : BaseActivity() {


    private val greetingsIdeas = arrayOf(
        "Merry Christmas and joy!",
        "Peace, love, and twinkle lights.",
        "Season's blessings to you.",
        "Warm cocoa, warmer hearts!",
        "Snowy wishes, merry kisses.",
        "Let it snow happiness!",
        "Magic and joy await!",
        "Hope shines this season.",
        "Cozy up, it’s Christmas!",
        "Cheers to holiday love.",
        "Twinkling stars, glowing hearts.",
        "Festive cheers, merry years!",
        "Santa’s sleigh is near!",
        "Joyful times, festive rhymes.",
        "Holly, jolly, and bright!",
        "Under the mistletoe glow.",
        "Candles flicker, hearts warm.",
        "A season of sparkling joy.",
        "Reindeer magic fills the air.",
        "Snowflakes and merry dreams.",
        "Gingerbread hugs and love.",
        "Santa's on his way!",
        "All is calm, all is bright.",
        "Believe in the holiday magic.",
        "Pine-scented wishes for you.",
        "Christmas cheer everywhere!",
        "Holiday hugs, festive mugs.",
        "Carols sung with pure joy.",
        "Shimmering lights, warm nights.",
        "Bells ringing, hearts singing.",
        "Unwrap joy this season!",
        "Sparkles, laughter, and love.",
        "Jingle bells and happy spells.",
        "Joy to the world, always!",
        "Bright stars, brighter hearts.",
        "Let Christmas cheer fill you!",
        "Candy canes and snowy lanes.",
        "Fa-la-la-la-la, it’s Christmas!",
        "Wishing you tidings of joy.",
        "Santa’s list has you on it!",
        "Reindeer footprints in snow.",
        "The season of warm hearts.",
        "Happy hearts, holiday starts.",
        "Glow with the Christmas spirit.",
        "Sweet treats, sweeter moments.",
        "Gifts wrapped in love.",
        "Let your heart feel merry.",
        "Frosty mornings, cozy evenings.",
        "Toasty hearts, festive starts.",
        "Wishing you endless wonder.",
        "Stars twinkle, hearts sprinkle.",
        "Crisp air, warm wishes.",
        "Joy sparkles everywhere!",
        "Christmas trees and warm teas.",
        "Snow-dusted magical days.",
        "Mistletoe, kisses, and cheer.",
        "Hugs, smiles, and holiday miles.",
        "Merry hearts, bright starts.",
        "Jolly vibes and glowing tides.",
        "Elves are hard at work!",
        "Presents, peace, and perfection.",
        "Holiday magic is here!",
        "Laughter and light abound.",
        "Share love this holiday season.",
        "Ornaments glow with cheer.",
        "Frosty fun, holiday sun.",
        "Baking cookies, spreading joy.",
        "Seasonal greetings from the heart.",
        "Deck the halls with joy!",
        "Silent nights, joyful sights.",
        "Yule magic is everywhere.",
        "Wrapping joy in every bow.",
        "Evergreen trees and happy bees.",
        "Festive spirit, warmest hugs.",
        "Wishing you frosty delight.",
        "Holiday warmth fills the air.",
        "Golden bells and snowy spells.",
        "Stars and stockings, all bright.",
        "Holiday hugs for everyone.",
        "Decked out in Christmas cheer.",
        "Rejoice in holiday splendor!",
        "Heartfelt greetings, festive meetings.",
        "Cheer abounds, magic surrounds.",
        "Dreams of snow and joy.",
        "Happy wishes, festive dishes.",
        "Cider, warmth, and celebration.",
        "Bright memories for you.",
        "Season’s greetings, warm meetings.",
        "May your holidays glow bright.",
        "Winter magic and bright stars.",
        "Sharing joy and heartfelt love.",
        "Wishing snowflakes and joy.",
        "Christmas cuddles and carols."


    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        val countdownText = findViewById<TextView>(R.id.CountdownText)
        val greetingText = findViewById<TextView>(R.id.GreetingText)
        val returnButton = findViewById<ImageButton>(R.id.ReturnButtonWhite)
        val viewHistoryButton = findViewById<ImageButton>(R.id.ViewGreetingHistoryButton)
        val newGreetingButton = findViewById<ImageButton>(R.id.NewGreetingButton)

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
                    handleGrinchInterference("greeting", greetingText)
                }
            }
        }
        handler.post(runnable)

        newGreetingButton.setOnClickListener {
            handleGrinchInterference("greeting", greetingText)
        }

        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }

        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, GreetingHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleGrinchInterference(type: String, displayText: TextView) {
        if (Random.nextInt(100) < 20) { // 20% chance Grinch interferes
            val grinchMessage = when (type) {
                "greeting" -> "Wishes canceled, the Grinch \nwas here first!\n \nGo back to main page."
                else -> "The Grinch strikes again!"
            }
            // Log Grinch interference to history
            HistoryManager.greetingHistory.add("💔Wishes canceled, the Grinch was here first!")
            // Launch GrinchActivity with the message
            val intent = Intent(this, GrinchActivity::class.java)
            intent.putExtra("grinchMessage", grinchMessage)
            startActivity(intent)
        } else {
            val message = when (type) {
                "greeting" -> greetingsIdeas.random()
                else -> "Unexpected choice"
            }
            displayText.text = message
            // Add to history only when greeting generation is successful
            if (type == "greeting") {
                HistoryManager.greetingHistory.add("💌 $message")
            }
        }

    } }