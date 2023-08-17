package com.example.testecarrefour.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testecarrefour.R
import com.example.testecarrefour.databinding.ListItemUserBinding
import com.example.testecarrefour.domain.models.User
import com.squareup.picasso.Picasso

class UsersAdapter(private val usersList: List<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    class ViewHolder(private var binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(currentUser: User) {
            Picasso.get().load(currentUser.userAvatar).resize(200, 200)
                .error(R.drawable.baseline_error_outline_24).centerCrop()
                .into(binding.userImage)
            binding.userName.text = currentUser.userName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener!!.onClick(usersList[position].userName)
        }
        holder.bindView(usersList[position])
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(currentUserName: String)
    }
}