//package com.aital.doskaykt
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.aital.doskaykt.databinding.ItemCategoryLayoutBinding
//import com.aital.doskaykt.databinding.ItemPostLayoutBinding
//import com.aital.doskaykt.models.Category
//import com.aital.doskaykt.models.Post
//import com.bumptech.glide.Glide
//
//class FilteredFeedAdapter : RecyclerView.Adapter<FilteredFeedAdapter.ViewHolder>() {
//
//    private var postsList = ArrayList<Post>()
//    private var onClickListener: OnClickListener? = null
//
//    fun setPostsList(postsList: List<Post>) {
//        this.postsList = postsList as ArrayList<Post>
//        notifyDataSetChanged()
//    }
//
//    class ViewHolder(val binding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            ItemPostLayoutBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                )
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
//        val item = postsList[position]
//        Glide.with(holder.itemView)
//            .load(item.picsUrl[0].url)
//            .into(holder.binding.postPic)
//        holder.binding.postTitle.text = item.title
//        holder.binding.postPrice.text = item.price
//        holder.binding.postDate.text = item.publicDate
//        holder.itemView.setOnClickListener {
//            if (onClickListener != null) {
//                onClickListener!!.onClick(position, item )
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return postsList.size
//    }
//
//    fun setOnClickListener(onClickListener: OnClickListener) {
//        this.onClickListener = onClickListener
//    }
//
//    interface OnClickListener {
//        fun onClick(position: Int, model: Category)
//    }
//}