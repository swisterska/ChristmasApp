package com.example.christmasapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private var musicButton: ImageButton? = null

    override fun onResume() {
        super.onResume()

        setupMusicButton()

        if (this is GrinchActivity) {
            MusicManager.playGrinchMusic(this)
        } else {
            if (MusicManager.isGrinchMusicPlaying()) {
                MusicManager.stopGrinchMusic(this)
            }
            if (MusicManager.isGloballyPaused()) {
                MusicManager.pause()
            } else {
                MusicManager.resumePlaylist(this)
            }
        }

        updateMusicButtonState()
    }

    override fun onPause() {
        super.onPause()
        MusicManager.saveCurrentState()
    }

    private fun setupMusicButton() {
        musicButton = findViewById(R.id.MusicOnButton)
        musicButton?.setOnClickListener {
            toggleMusic()
        }
    }

    private fun toggleMusic() {
        if (MusicManager.isMusicPlaying()) {
            MusicManager.pause()
            musicButton?.setImageResource(R.drawable.musicoffbutton)
        } else {
            MusicManager.unpauseGlobally()
            if (this is GrinchActivity) {
                MusicManager.playGrinchMusic(this)
            } else {
                MusicManager.resumePlaylist(this)
            }
            musicButton?.setImageResource(R.drawable.musiconbutton)
        }
    }

    private fun updateMusicButtonState() {
        val isPlaying = MusicManager.isMusicPlaying()
        musicButton?.setImageResource(if (isPlaying) R.drawable.musiconbutton else R.drawable.musicoffbutton)
    }
}
