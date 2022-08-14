package uz.isystem.tmdbapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationComponent = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        binding.mainBottomView.setupWithNavController(navigationComponent)

    }
}