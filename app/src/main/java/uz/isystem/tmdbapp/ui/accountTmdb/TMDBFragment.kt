package uz.isystem.tmdbapp.ui.accountTmdb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.isystem.tmdbapp.databinding.FragmentTmdbBinding

class TMDBFragment : Fragment() {

    private var binding: FragmentTmdbBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTmdbBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.info.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentInfo()
            findNavController().navigate(action)
        }
        binding!!.favourite.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentFavorite()
            findNavController().navigate(action)
        }
        binding!!.contact.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentContact()
            findNavController().navigate(action)
        }

        binding!!.singIn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/"))
            startActivity(browserIntent)

        }
    }
}
