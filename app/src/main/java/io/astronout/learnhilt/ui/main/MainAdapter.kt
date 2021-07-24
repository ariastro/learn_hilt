package io.astronout.learnhilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.astronout.learnhilt.data.model.User
import io.astronout.learnhilt.databinding.ItemLayoutBinding

class MainAdapter(private val users: List<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }
}