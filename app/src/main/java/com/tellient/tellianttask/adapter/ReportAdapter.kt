package com.tellient.tellianttask.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tellient.tellianttask.R
import com.tellient.tellianttask.data.AppData
import kotlinx.android.synthetic.main.list_item_category.view.*
import kotlinx.android.synthetic.main.list_item_schdedule_list.view.*
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import android.text.style.UnderlineSpan


class ReportAdapter(val patientDetails: ArrayList<AppData.ReportDetails>, val mContext: Context) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    val reg1 = "Completed by"
    val reg2 = "Assigned to"

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_schdedule_list, parent, false))
    }

    override fun getItemCount(): Int {
        return patientDetails.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.bind(patientDetails.get(pos))
    }

    fun deleteItem(pos: Int) {
        patientDetails.removeAt(pos)
//        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(view: View?) {
            deleteItem(adapterPosition)

        }

        fun bind(report: AppData.ReportDetails) {
            view.ivStatusImage.setImageResource(report.statusIcon)
            view.tvStatus.text = report.status
            view.tvStatus.setTextColor(updateColor(report.status))
            view.tvType.text = report.type
            view.tvConsultTime.text = report.timimg
            view.tvDocStatus.text = generateSpanText(report.reportedBy)
            view.tvCreatedBy.text = report.createdBy
            view.ivDelete.setOnClickListener(this)
            view.rootView.setBackgroundColor(if (adapterPosition % 2 == 0) ContextCompat.getColor(mContext, R.color.gray) else Color.WHITE)
        }

        fun updateColor(color: String): Int {
            return when (color) {
                "Completed" -> {
                    return ContextCompat.getColor(mContext, R.color.green)
                }
                "In Progress" -> {
                    return ContextCompat.getColor(mContext, R.color.yellow)
                }
                else -> {
                    return ContextCompat.getColor(mContext, R.color.colorPrimaryDark)
                }
            }
        }

        fun generateSpanText(text: String): SpannableString {
            val wordtoSpan = SpannableString(text)
            var startIndex = 0

            if (text.startsWith(reg1))
                startIndex = reg1.length
            if (text.startsWith(reg2))
                startIndex = reg2.length

            wordtoSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorPrimaryDark)), startIndex, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            wordtoSpan.setSpan(UnderlineSpan(),startIndex+1, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return wordtoSpan
        }

    }
}