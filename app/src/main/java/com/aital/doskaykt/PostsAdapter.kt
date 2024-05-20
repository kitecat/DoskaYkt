package com.aital.doskaykt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aital.doskaykt.databinding.ItemPostLayoutBinding
import com.bumptech.glide.Glide

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    private var postsList = ArrayList<Post>()
    fun setPostsList(postsList: List<Post>) {
        this.postsList = postsList as ArrayList<Post>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(postsList[position].picsUrl[0].url)
            .into(holder.binding.postPic)
        holder.binding.postTitle.text = postsList[position].title
    }

    override fun getItemCount(): Int {
        return postsList.size
    }
}