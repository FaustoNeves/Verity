package com.example.testecarrefour.ui.reposinfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.databinding.FragmentUserInfoBottomSheetDialogBinding
import com.example.testecarrefour.utils.IdleResource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentUserInfoBottomSheetDialogBinding
    private val viewModel: RepositoriesViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoriesAdapter
    private val args: RepositoriesBottomSheetDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupErrorOnRequest()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        recyclerView = binding.reposRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.isRequestCompleted.observe(viewLifecycleOwner) {
            adapter = RepositoriesAdapter(viewModel.reposListLiveData.value!!, requireContext())
            recyclerView.adapter = adapter
            setupClickActionForAdapter()
            hideProgressBar()
        }

        viewModel.getRepositoriesList(args.userName)
        showProgressBar()
    }

    private fun setupClickActionForAdapter() {
        adapter.setOnClickListener(object : RepositoriesAdapter.OnClickListener {
            override fun onClick(repositoryUrl: String) {
                startActivity(Intent(Intent.ACTION_VIEW, repositoryUrl.toUri()))
            }
        })
    }

    private fun setupErrorOnRequest() {
        viewModel.requestThrowable.observe(viewLifecycleOwner) {
            hideProgressBar()
            Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBar() {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
        binding.repositoriesPg.visibility = View.VISIBLE
        IdleResource.increment()
    }

    private fun hideProgressBar() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        binding.repositoriesPg.visibility = View.GONE
        IdleResource.decrement()
    }
}