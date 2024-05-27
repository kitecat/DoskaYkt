package com.aital.doskaykt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aital.doskaykt.databinding.ItemCategoryLayoutBinding
import com.aital.doskaykt.models.Rubric

class RubricsAdapter : RecyclerView.Adapter<RubricsAdapter.ViewHolder>() {

    private var rubricsList = ArrayList<Rubric>()
    private var onClickListener: OnClickListener? = null

    fun setRubricsList(rubricsList: List<Rubric>) {
        this.rubricsList = rubricsList as ArrayList<Rubric>
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
        val item = rubricsList[position]
        holder.binding.categoryName.text = item.name
        holder.binding.postsCount.text = item.count.toString()
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item )
            }
        }
    }

    override fun getItemCount(): Int {
        return rubricsList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Rubric)
    }
}