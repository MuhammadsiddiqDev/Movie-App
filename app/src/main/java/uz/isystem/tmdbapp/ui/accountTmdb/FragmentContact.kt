package uz.isystem.tmdbapp.ui.accountTmdb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.isystem.tmdbapp.databinding.FragmentContactBinding

class FragmentContact : Fragment() {

    private var binding: FragmentContactBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "onViewCreated", Toast.LENGTH_SHORT)


        binding!!.telegram.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Muhammadsiddiq_97"))
            startActivity(browserIntent)
        }
        binding!!.instagram.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/android_developer_2022/")
            )
            startActivity(browserIntent)
        }
        binding!!.linkedin.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/muhammadsiddiq-mirzaxmedov-752b91232/")
            )
            startActivity(browserIntent)
        }


    }
}