package com.anle.democache.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anle.democache.R
import com.anle.democache.models.UserInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val books: ArrayList<UserInfo>, private val onItemClicked: (id: Int) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindView(books[position], onItemClicked)
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(userInfo: UserInfo, onItemClicked: (id: Int) -> Unit) {
            itemView.tvUserName.text = "${userInfo.id} - ${userInfo.name}"
            itemView.tvCreatedDate.text = "Create at: ${userInfo.createdAt}"
            Glide.with(itemView)
                .load(userInfo.avatar)
                .into(itemView.imgAvatar)

            itemView.setOnClickListener {
                onItemClicked.invoke(userInfo.id)
            }
        }
    }
}