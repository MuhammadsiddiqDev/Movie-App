package com.example.movieapp.ui.main.trailer

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.movieapp.core.utils.CONST
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import uz.isystem.tmdbapp.databinding.ActivityTrailerBinding

class TrailerActivity : YouTubeBaseActivity() {

    companion object {
        val KEY_LINK = "video_link"
    }

    private lateinit var binding: ActivityTrailerBinding

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val link = it.getString(KEY_LINK)
            binding.playerView.initialize(CONST.DEVELOPER_KEY, object :
                YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(link)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {

                }

            })
        }
    }

}
