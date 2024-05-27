package com.aital.doskaykt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aital.doskaykt.databinding.ItemCategoryLayoutBinding
import com.aital.doskaykt.models.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var categoriesList = ArrayList<Category>()
    private var onClickListener: OnClickListener? = null

    fun setCategoriesList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoriesList[position]
        holder.binding.categoryName.text = item.name
        holder.binding.postsCount.text = item.count.toString()
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item )
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Category)
    }
}