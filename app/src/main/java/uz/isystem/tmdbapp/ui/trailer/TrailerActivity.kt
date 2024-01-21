package uz.isystem.tmdbapp.ui.trailer

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import uz.isystem.tmdbapp.databinding.ActivityTrailerBinding

class TrailerActivity : AppCompatActivity() {

    private var youtubePlayer: YouTubePlayer? = null

    private var _binding: ActivityTrailerBinding? = null
    private val binding get() = _binding!!

    companion object {
        val KEY_LINK = "video_link"
    }

    private var isFullScreen = false

    // ... (other declarations)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullScreen) {
                youtubePlayer?.toggleFullscreen()
            } else {
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addFullscreenListener(object : FullscreenListener {

            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true
                binding.FullScreenCon.visibility = View.VISIBLE
                binding.FullScreenCon.addView(fullscreenView)

                WindowInsetsControllerCompat(window!!, binding.root).apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }
            }

            override fun onExitFullscreen() {
                isFullScreen = false
                binding.FullScreenCon.visibility = View.VISIBLE
                binding.FullScreenCon.removeAllViews()

                WindowInsetsControllerCompat(window!!, binding.root).apply {
                    show(WindowInsetsCompat.Type.systemBars())
                }
                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                }
            }

        })

        val youtubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@TrailerActivity.youtubePlayer = youTubePlayer
                val intent = intent
                val videoId = intent.getStringExtra(KEY_LINK).toString()

                if (videoId == "") {
                    youtubePlayer?.loadOrCueVideo(lifecycle, "isRO2_6nNlc", 0f)
                } else {
                    youtubePlayer?.loadOrCueVideo(lifecycle, videoId, 0f)

                }
            }
        }

        val iFramePlayerOption = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1)
            .build()

        binding.youtubePlayerView.enableAutomaticInitialization = false
        binding.youtubePlayerView.initialize(youtubePlayerListener, iFramePlayerOption)
    }

    // ... (other methods)

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (!isFullScreen) {
                youtubePlayer?.toggleFullscreen()
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (isFullScreen) {
                youtubePlayer?.toggleFullscreen()
            }
        }
    }
}

