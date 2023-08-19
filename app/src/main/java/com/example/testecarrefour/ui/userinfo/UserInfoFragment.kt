package com.example.testecarrefour.ui.userinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.R
import com.example.testecarrefour.databinding.FragmentUserInfoBinding
import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.UserProfile
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private val args: UserInfoFragmentArgs by navArgs()
    private val viewModel: UserInfoViewModel by viewModels()
    private lateinit var binding: FragmentUserInfoBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.reposRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setupErrorOnRequest()

        viewModel.isRequestCompleted.observe(viewLifecycleOwner) {
            setupUserProfileUI(viewModel.userProfileLiveData.value!!)
            adapter = RepositoriesAdapter(viewModel.reposListLiveData.value!!, requireContext())
            recyclerView.adapter = adapter
            setupClickActionForAdapter()
        }

        if (args.userName.isNotBlank()) {
            viewModel.getUserProfile(args.userName)
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
            publicRepos.text = requireContext().getString(R.string.public_repos_text, userProfile.publicRepos.toString())
            followers.text = requireContext().getString(R.string.followers_text, userProfile.followers.toString())
            following.text = requireContext().getString(R.string.following_text, userProfile.following.toString())
            setupClickActionVisitProfile(userProfile.profileUrl)
        }
    }

    private fun setupErrorOnRequest() {
        viewModel.requestThrowable.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupClickActionForAdapter() {
        adapter.setOnClickListener(object:RepositoriesAdapter.OnClickListener{
            override fun onClick(repositoryUrl: String) {
                startActivity(Intent(Intent.ACTION_VIEW, repositoryUrl.toUri()))
            }
        })
    }

    private fun setupClickActionVisitProfile(profileUrl: String) {
        binding.visitProfile.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, profileUrl.toUri()))
        }
    }

//    override fun onUniversityClick(position: Int) {
//        try {
//            val universityItem = listOfUniversities!![position]
//            val uri = Uri.parse(universityItem.web_pages!![0])
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        } catch (e: ActivityNotFoundException) {
//            displayMessage("Error")
//        }
//    }
}