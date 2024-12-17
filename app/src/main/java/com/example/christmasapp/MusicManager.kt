package com.example.christmasapp

import android.content.Context
import android.media.MediaPlayer

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentSongIndex = 0
    private var currentPlaybackPosition = 0
    private val playlist = listOf(
        R.raw.jinglebellrock,
        R.raw.lastchristmas
    )
    private var isGrinchMusic = false
    private val grinchMusic = R.raw.grinchmusic
    private var isMusicPausedGlobally = false

    fun isMusicPlaying(): Boolean {
        return mediaPlayer?.isPlaying == true && !isMusicPausedGlobally
    }

    fun isGloballyPaused(): Boolean {
        return isMusicPausedGlobally
    }

    fun isGrinchMusicPlaying(): Boolean {
        return isGrinchMusic
    }

    fun play(context: Context) {
        if (isGrinchMusic || isMusicPausedGlobally) return
        if (mediaPlayer == null) {
            initializeMediaPlayer(context, playlist[currentSongIndex])
        }
        mediaPlayer?.apply {
            seekTo(currentPlaybackPosition)
            start()
        }
    }

    private fun initializeMediaPlayer(context: Context, resource: Int) {
        mediaPlayer?.release() // Prevent resource leaks
        mediaPlayer = MediaPlayer.create(context, resource)
        mediaPlayer?.setOnCompletionListener {
            if (!isGrinchMusic) {
                playNext(context)
            }
        }
    }

    private fun playNext(context: Context) {
        currentSongIndex = (currentSongIndex + 1) % playlist.size
        initializeMediaPlayer(context, playlist[currentSongIndex])
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        currentPlaybackPosition = 0
    }

    fun pause() {
        mediaPlayer?.apply {
            currentPlaybackPosition = currentPosition
            pause()
        }
        isMusicPausedGlobally = true
    }

    fun resume() {
        if (isMusicPausedGlobally) {
            mediaPlayer?.apply {
                seekTo(currentPlaybackPosition)
                if (!isPlaying) start()
            }
            isMusicPausedGlobally = false
        }
    }

    fun playGrinchMusic(context: Context) {
        if (isGrinchMusic) {
            // If Grinch music was paused, resume it
            if (isMusicPausedGlobally) {
                isMusicPausedGlobally = false
                mediaPlayer?.apply {
                    seekTo(currentPlaybackPosition)
                    if (!isPlaying) start()
                }
            }
            return
        }

        // Otherwise, play Grinch music from the start
        saveCurrentState() // Save the playlist state
        stop() // Stop any current music
        initializeMediaPlayer(context, grinchMusic)
        mediaPlayer?.apply {
            isLooping = true
            start()
        }
        isGrinchMusic = true
        isMusicPausedGlobally = false
    }

    fun stopGrinchMusic(context: Context) {
        if (!isGrinchMusic) return
        stop()
        isGrinchMusic = false
        restorePlaylistState(context) // Restore the saved playlist state
    }

    fun saveCurrentState() {
        if (!isGrinchMusic) {
            currentPlaybackPosition = mediaPlayer?.currentPosition ?: 0
        }
    }

    fun restorePlaylistState(context: Context) {
        if (isMusicPausedGlobally) return
        if (mediaPlayer == null) {
            initializeMediaPlayer(context, playlist[currentSongIndex])
        }
        mediaPlayer?.apply {
            seekTo(currentPlaybackPosition)
            start()
        }
    }

    fun resumePlaylist(context: Context) {
        if (isGrinchMusic) {
            stopGrinchMusic(context)
        }
        if (isMusicPausedGlobally) return
        if (mediaPlayer == null) {
            initializeMediaPlayer(context, playlist[currentSongIndex])
        }
        mediaPlayer?.apply {
            seekTo(currentPlaybackPosition)
            start()
        }
    }

    fun unpauseGlobally() {
        isMusicPausedGlobally = false
    }
}
