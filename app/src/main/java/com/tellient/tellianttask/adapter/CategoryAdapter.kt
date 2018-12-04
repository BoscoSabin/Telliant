package com.tellient.tellianttask.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tellient.tellianttask.R
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoryAdapter(val categoryData: Array<String>, val mContext: Context ) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var mSelectedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryData.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.bind(categoryData.get(pos))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        fun bind(category: String) {
            view.tvCategory.text = category

            if (mSelectedPos == adapterPosition) {
                view.tvCategory.setBackgroundResource(R.drawable.corner_rectangle_selected)
                view.tvCategory.setTextColor(Color.WHITE)
            } else {
                view.tvCategory.setBackgroundResource(R.drawable.corner_rectangle_unselected)
                view.tvCategory.setTextColor(ContextCompat.getColor(mContext, R.color.textColor))
            }

            view.tvCategory.setOnClickListener {
                mSelectedPos = adapterPosition
                notifyDataSetChanged()
            }
        }

    }
}