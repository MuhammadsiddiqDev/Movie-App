package uz.isystem.tmdbapp.ui.zoomImage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.databinding.ActivityZoomImagesBinding

class ActivityZoomImages : AppCompatActivity() {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
    }

    private lateinit var MovieImage: String

    private var _binding: ActivityZoomImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {


        var intent = intent
        MovieImage = intent.getStringExtra(MOVIE_DATA)!!


        super.onCreate(savedInstanceState)
        _binding = ActivityZoomImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        Glide.with(binding.imageView)
            .load("https://image.tmdb.org/t/p/w500$MovieImage")
            .into(binding.imageView)

    }


}