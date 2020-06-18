package com.aday.abook.feature.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aday.abook.R

class SettingAdapter(
    private val click: (Int) -> Unit
): RecyclerView.Adapter<SettingAdapter.ViewHolder>() {
    private val settingList: List<String> = listOf("비밀번호 설정하기", "앱 평가하기")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_setting_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = settingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(settingList[position])
        holder.itemView.setOnClickListener { click(position) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val settingText = itemView.findViewById<TextView>(R.id.settingText)

        fun bind(v: String) {
            settingText.text = v
        }
    }
}