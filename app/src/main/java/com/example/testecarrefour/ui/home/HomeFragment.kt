package com.example.testecarrefour.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.databinding.FragmentHomeBinding
import com.example.testecarrefour.utils.EMPTY_INPUT_SEARCH_FIELD
import com.example.testecarrefour.utils.IdleResource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListenersForErrors()
        initializeUsersRecyclerView()
        setupClickActionForSearch()
    }

    private fun initializeUsersRecyclerView() {
        usersRecyclerView = binding.usersRecyclerView
        usersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.usersList.observe(viewLifecycleOwner) {
            usersAdapter = UsersAdapter(it)
            usersRecyclerView.adapter = usersAdapter
            setupClickActionForAdapter()
            hideProgressBar()
        }
        viewModel.getUsers()
        showProgressBar()
    }

    private fun setupClickActionForAdapter() {
        usersAdapter.setOnClickListener(object : UsersAdapter.OnClickListener {
            override fun onClick(currentUserName: String) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToUserInfoFragment(currentUserName)
                findNavController().navigate(action)
            }
        })
    }

    private fun setupClickActionForSearch() {
        viewModel.initializeNavigation.observe(viewLifecycleOwner) {
            if (it) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToUserInfoFragment(
                        null,
                        viewModel.userProfile.value
                    )
                findNavController().navigate(action)
            }
        }

        binding.searchButton.setOnClickListener {
            viewModel.getUser(binding.searchFieldEditText.text.toString())
        }
    }

    private fun setupListenersForErrors() {
        viewModel.requestThrowableOn.observe(viewLifecycleOwner) {
            hideProgressBar()
            Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.throwableOnGetUser.observe(viewLifecycleOwner) {
            IdleResource.decrement()
            if (it) {
                binding.searchFieldEditText.error = EMPTY_INPUT_SEARCH_FIELD
                binding.searchFieldEditText.requestFocus()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.initializeNavigation.value = false
        viewModel.throwableOnGetUser.value = false
    }

    private fun showProgressBar() {
        IdleResource.increment()
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        binding.homePg.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        binding.homePg.visibility = View.GONE
        IdleResource.decrement()
    }
}