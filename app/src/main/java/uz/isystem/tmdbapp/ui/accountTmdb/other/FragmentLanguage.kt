package uz.isystem.tmdbapp.ui.accountTmdb.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.locale.LocaleManager
import uz.isystem.tmdbapp.databinding.FragmentLanguageBinding

class FragmentLanguage : Fragment() {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAction()

        if (AppCache.appCache?.getLanguage() == "en") {
            binding.eng.isChecked = true
            binding.rus.isChecked = false
        } else {
            binding.eng.isChecked = false
            binding.rus.isChecked = true
        }


    }

    private fun clickAction() {

        binding.eng.setOnClickListener {

            if (AppCache.appCache?.getLanguage() != "en") {

                AppCache.appCache?.setLanguage("en")
                LocaleManager.setLang(requireContext())

                activity?.recreate()

            }
        }

        binding.rus.setOnClickListener {

            if (AppCache.appCache?.getLanguage() != "ru") {

                AppCache.appCache?.setLanguage("ru")
                LocaleManager.setLang(requireContext())

                activity?.recreate()
            }
        }
    }

}