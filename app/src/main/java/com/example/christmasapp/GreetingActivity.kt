package com.example.christmasapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class GreetingActivity : AppCompatActivity() {

    private val greetingsIdeas = arrayOf(
        "Merry Christmas \nand joy!",
        "Peace, love, \nand twinkle lights.",
        "Season's blessings \nto you.",
        "Warm cocoa, \nwarmer hearts!",
        "Snowy wishes, \nmerry kisses.",
        "Let it snow \nhappiness!",
        "Magic and joy \nawait!",
        "Hope shines \nthis season.",
        "Cozy up, \nit’s Christmas!",
        "Cheers to \nholiday love.",
        "Twinkling stars, \nglowing hearts.",
        "Festive cheers, \nmerry years!",
        "Santa’s sleigh \nis near!",
        "Joyful times, \nfestive rhymes.",
        "Holly, jolly, \nand bright!",
        "Under the mistletoe \nglow.",
        "Candles flicker, \nhearts warm.",
        "A season of \nsparkling joy.",
        "Reindeer magic \nfills the air.",
        "Snowflakes and \nmerry dreams.",
        "Gingerbread hugs \nand love.",
        "Santa's on \nhis way!",
        "All is calm, \nall is bright.",
        "Believe in the \nholiday magic.",
        "Pine-scented wishes \nfor you.",
        "Christmas cheer \neverywhere!",
        "Holiday hugs, \nfestive mugs.",
        "Carols sung \nwith pure joy.",
        "Shimmering lights, \nwarm nights.",
        "Bells ringing, \nhearts singing.",
        "Unwrap joy \nthis season!",
        "Sparkles, laughter, \nand love.",
        "Jingle bells and \nhappy spells.",
        "Joy to the world, \nalways!",
        "Bright stars, \nbrighter hearts.",
        "Let Christmas cheer \nfill you!",
        "Candy canes and \nsnowy lanes.",
        "Fa-la-la-la-la, \nit’s Christmas!",
        "Wishing you \ntidings of joy.",
        "Santa’s list \nhas you on it!",
        "Reindeer footprints \nin snow.",
        "The season of \nwarm hearts.",
        "Happy hearts, \nholiday starts.",
        "Glow with the \nChristmas spirit.",
        "Sweet treats, \nsweeter moments.",
        "Gifts wrapped \nin love.",
        "Let your heart \nfeel merry.",
        "Frosty mornings, \ncozy evenings.",
        "Toasty hearts, \nfestive starts.",
        "Wishing you \nendless wonder.",
        "Stars twinkle, \nhearts sprinkle.",
        "Crisp air, \nwarm wishes.",
        "Joy sparkles \neverywhere!",
        "Christmas trees \nand warm teas.",
        "Snow-dusted \nmagical days.",
        "Mistletoe, kisses, \nand cheer.",
        "Hugs, smiles, \nand holiday miles.",
        "Merry hearts, \nbright starts.",
        "Jolly vibes \nand glowing tides.",
        "Elves are hard \nat work!",
        "Presents, peace, \nand perfection.",
        "Holiday magic \nis here!",
        "Laughter and \nlight abound.",
        "Share love this \nholiday season.",
        "Ornaments glow \nwith cheer.",
        "Frosty fun, \nholiday sun.",
        "Baking cookies, \nspreading joy.",
        "Seasonal greetings \nfrom the heart.",
        "Deck the halls \nwith joy!",
        "Silent nights, \njoyful sights.",
        "Yule magic \nis everywhere.",
        "Wrapping joy \nin every bow.",
        "Evergreen trees \nand happy bees.",
        "Festive spirit, \nwarmest hugs.",
        "Wishing you \nfrosty delight.",
        "Holiday warmth \nfills the air.",
        "Golden bells and \nsnowy spells.",
        "Stars and stockings, \nall bright.",
        "Holiday hugs \nfor everyone.",
        "Decked out in \nChristmas cheer.",
        "Rejoice in \nholiday splendor!",
        "Heartfelt greetings, \nfestive meetings.",
        "Cheer abounds, \nmagic surrounds.",
        "Dreams of snow \nand joy.",
        "Happy wishes, \nfestive dishes.",
        "Cider, warmth, \nand celebration.",
        "Bright memories \nfor you.",
        "Season’s greetings, \nwarm meetings.",
        "May your holidays \nglow bright.",
        "Winter magic and \nbright stars.",
        "Sharing joy and \nheartfelt love.",
        "Wishing snowflakes \nand joy.",
        "Christmas cuddles \nand carols."

    )

    private val history = mutableListOf<String>()

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
                    // Display random greeting after countdown with Grinch logic
                    countdownText.text = "" // Clear the countdown
                    handleGrinchInterference("greeting", greetingText)
                }
            }
        }
        handler.post(runnable)

        newGreetingButton.setOnClickListener {
            handleGrinchInterference("greeting", greetingText)
        }

        // Return button logic
        returnButton.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }

        // View history button logic
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
            history.add(grinchMessage)
            // Launch GrinchActivity with the message
            val intent = Intent(this, GrinchActivity::class.java)
            intent.putExtra("grinchMessage", grinchMessage)
            startActivity(intent)
        } else {
            val message = when (type) {
                "greeting" -> greetingsIdeas.random()
                else -> "Unexpected choice"
            }
            history.add(message)
            displayText.text = message
        }
    }}