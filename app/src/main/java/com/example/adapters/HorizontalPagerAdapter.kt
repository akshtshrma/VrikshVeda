// HorizontalPagerAdapter.kt
package com.example.vrikshveda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vrikshveda.R

class HorizontalPagerAdapter : RecyclerView.Adapter<HorizontalPagerAdapter.PagerViewHolder>() {

    private val items = listOf(
        ItemData(R.drawable.item_3, "Aloe Vera", "Description 1"),
        ItemData(R.drawable.item_4, "Tulsi", "Description 2"),
        ItemData(R.drawable.item_3, "Neem", "Description 3"),
        ItemData(R.drawable.item_4, "Corriander", "Description 1"),
        ItemData(R.drawable.item_3, "Aloe Vera", "Description 2"),
        ItemData(R.drawable.item_4, "Tulsi", "Description 3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_pager, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    // ViewHolder to bind data to views
    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        private val titleView: TextView = itemView.findViewById(R.id.itemTitle)
        private val subtitleView: TextView = itemView.findViewById(R.id.itemSubtitle)

        fun bind(item: ItemData) {
            imageView.setImageResource(item.imageResId)
            titleView.text = item.title
            subtitleView.text = item.subtitle
        }
    }

    data class ItemData(val imageResId: Int, val title: String, val subtitle: String)
}
