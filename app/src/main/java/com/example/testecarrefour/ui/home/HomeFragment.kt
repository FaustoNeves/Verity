package com.example.testecarrefour.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

//TODO Adicionar busca pela barra de pesquisa pelo nome
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

        setupErrorOnRequest()
        initializeUsersRecyclerView()
    }

    private fun initializeUsersRecyclerView() {
        usersRecyclerView = binding.usersRecyclerView
        usersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.user.observe(viewLifecycleOwner) {
            usersAdapter = UsersAdapter(it)
            usersRecyclerView.adapter = usersAdapter
            setupClickActionForAdapter()
        }
        viewModel.getUsers()
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

    private fun setupErrorOnRequest() {
        viewModel.requestThrowable.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }

//    private fun showProgressBar() {
//        binding.dark_background_login.visibility = View.VISIBLE
//        binding.login_button.setBackgroundColor(R.color.orange_2_dark)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//        );
//        binding.login_progress_bar.visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBar() {
//        binding.login_button.setBackgroundResource(R.color.orange_2)
//        binding.dark_background_login.visibility = View.GONE
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        binding.login_progress_bar.visibility = View.GONE
//    }
}