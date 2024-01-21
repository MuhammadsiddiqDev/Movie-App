package uz.isystem.tmdbapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.databinding.ActivityMainBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun getView(): View {

        _binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        val navigationComponent = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        binding.mainBottomView.setupWithNavController(navigationComponent)

    }

}