package com.example.testecarrefour.ui.userinfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.test.espresso.IdlingResource
import com.example.testecarrefour.R
import com.example.testecarrefour.databinding.FragmentUserInfoBinding
import com.example.testecarrefour.domain.models.UserProfile
import com.example.testecarrefour.utils.IdleResource
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private val args: UserInfoFragmentArgs by navArgs()
    private val viewModel: UserInfoViewModel by viewModels()
    private lateinit var binding: FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupErrorOnRequest()

        viewModel.isRequestSucceded.observe(viewLifecycleOwner) {
            hideProgressBar()
            setupUserProfileUI(viewModel.userProfileLiveData.value!!)
        }

        if (!args.userName.isNullOrBlank()) {
            showProgressBar()
            viewModel.getUserProfile(args.userName!!)
        }
        if (args.userProfile != null) {
            setupUserProfileUI(args.userProfile!!)
        }
    }

    private fun setupUserProfileUI(userProfile: UserProfile) {
        binding.apply {
            Picasso.get().load(userProfile.userAvatar).resize(500, 500)
                .error(R.drawable.baseline_error_outline_24).centerCrop().into(binding.userImage)
            userName.text = userProfile.userName
            if (userProfile.company.isNullOrBlank())
                company.visibility = View.GONE
            company.text = requireContext().getString(R.string.company_text, userProfile.company)
            if (userProfile.location.isNullOrBlank())
                location.visibility = View.GONE
            location.text = userProfile.location
            publicRepos.text = requireContext().getString(
                R.string.public_repos_text,
                userProfile.publicRepos.toString()
            )
            followers.text = requireContext().getString(
                R.string.followers_text,
                userProfile.followers.toString()
            )
            following.text = requireContext().getString(
                R.string.following_text,
                userProfile.following.toString()
            )
            setupClickActionVisitProfile(userProfile.profileUrl!!)
            setupClickActionVisitRepositories(userProfile.userLogin!!)
        }
    }

    private fun setupErrorOnRequest() {
        viewModel.requestThrowable.observe(viewLifecycleOwner) {
            hideProgressBar()
            Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupClickActionVisitProfile(profileUrl: String) {
        binding.checkoutProfile.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, profileUrl.toUri()))
        }
    }

    private fun setupClickActionVisitRepositories(userLogin: String) {
        binding.checkoutRepos.setOnClickListener {
            val action =
                UserInfoFragmentDirections.actionUserInfoFragmentToUserInfoBottomSheetDialog(
                    userLogin
                )
            findNavController().navigate(action)
        }
    }

    private fun showProgressBar() {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
        binding.userInfoPg.visibility = View.VISIBLE
        IdleResource.increment()
    }

    private fun hideProgressBar() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        binding.userInfoPg.visibility = View.GONE
        IdleResource.decrement()
    }
}