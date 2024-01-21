package uz.isystem.tmdbapp.ui.accountTmdb

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse
import uz.isystem.tmdbapp.databinding.FragmentTmdbBinding
import uz.isystem.tmdbapp.ui.login.LoginActivity
import uz.isystem.tmdbapp.ui.login.LoginMVP
import uz.isystem.tmdbapp.ui.login.LoginPresenter

class TMDBFragment : Fragment(), LoginMVP.View {

    private var _binding: FragmentTmdbBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: LoginMVP.Presenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTmdbBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (AppCache.appCache?.isFirstOpen() == true) {
            binding.progressBar.visibility = View.GONE
            binding.logOut.visibility = View.GONE
            binding.logIn.visibility = View.VISIBLE
            binding.imageProfile.visibility = View.GONE
            binding.name.visibility = View.GONE
            binding.lastName.visibility = View.GONE
        } else {
            presenter = LoginPresenter(this)
            presenter.getAccountDetail()
            binding.logOut.visibility = View.VISIBLE
            binding.logIn.visibility = View.GONE
            binding.imageProfile.visibility = View.VISIBLE
            binding.name.visibility = View.VISIBLE
            binding.lastName.visibility = View.VISIBLE
        }



        binding.name.text = AppCache.appCache!!.getName()
        binding.lastName.text = AppCache.appCache!!.getUsername()

        Glide.with(binding.imageProfile)
            .load(AppCache.appCache!!.getImage())
            .into(binding.imageProfile)


        binding.info.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentInfo()
            findNavController().navigate(action)
        }
        binding.favourite.setOnClickListener {

            if (AppCache.appCache?.isFirstOpen() == true) {
                var intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentFavorite()
                findNavController().navigate(action)
            }

        }

        binding.logIn.setOnClickListener {

            var intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

        }
        binding.contact.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentContact()
            findNavController().navigate(action)
        }

        binding.language.setOnClickListener {
            val action = TMDBFragmentDirections.actionTMDBFragmentToFragmentLanguage()
            findNavController().navigate(action)
        }

        binding.logOut.setOnClickListener {
            showDialogDelete()
        }

        binding.imageProfile.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/"))
            startActivity(browserIntent)

        }
    }

    private fun showDialogDelete() {

        val layoutInflater: LayoutInflater = layoutInflater
        val view: View = layoutInflater.inflate(R.layout.custom_layout, null)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext(), R.style.dialog)
        var dialog: AlertDialog = builder.setView(view).create()

        val yesBtn = view.findViewById(R.id.button_yes) as Button
        val noBtn = view.findViewById(R.id.button_not) as Button

        yesBtn.setOnClickListener {
            AppCache.appCache?.setFirstOpen(true)
            AppCache.appCache?.deleteSession()
            AppCache.appCache?.deleteToken()
            AppCache.appCache?.deleteName()
            AppCache.appCache?.deleteUsername()
            AppCache.appCache?.deleteImage()

            var intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            dialog.dismiss()
            activity?.finish()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog = builder.show()
    }

    override fun isLoading(isLoading: Boolean) {

    }

    override fun setData(any: Any) {
    }

    override fun createRequestToken(token: String) {
    }

    override fun createSessionWithLogin(response: CreateRequestTokenResponse) {
    }

    override fun createSession(sessionId: String) {
    }

    override fun accountDetail(accountDetail: AccountDetailResponse) {

    }

    override fun onError(message: String?) {

        binding.progressBar.visibility = View.GONE
        binding.notInternetImage.visibility = View.VISIBLE
        binding.notInternetText.visibility = View.VISIBLE
        binding.scroll.visibility = View.GONE
    }
}
