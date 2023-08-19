package com.example.testecarrefour.ui.userinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.R
import com.example.testecarrefour.databinding.ListItemRepositoryBinding
import com.example.testecarrefour.domain.models.GitRepo

class RepositoriesAdapter(private val gitReposList: List<GitRepo>, private val context: Context) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    inner class ViewHolder(private val binding: ListItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(currentRepo: GitRepo) {
            binding.repoName.text = context.getString(R.string.repo_name, currentRepo.name)
            binding.repoDescription.text = context.getString(R.string.repo_description, currentRepo.description)
            binding.repoLanguage.text = context.getString(R.string.language, currentRepo.language)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gitReposList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener!!.onClick(gitReposList[position].htmlUrl)
        }
        holder.bindView(gitReposList[position])
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(repositoryUrl: String)
    }
}