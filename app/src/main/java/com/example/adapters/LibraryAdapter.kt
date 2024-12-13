package com.example.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vrikshveda.Library
import com.example.vrikshveda.R

class LibraryAdapter(private val libraryList: List<Library>) :
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    class LibraryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val libraryImage: ImageView = itemView.findViewById(R.id.libImage)
        val libraryTitle: TextView = itemView.findViewById(R.id.libTitle)
        val sciName: TextView = itemView.findViewById(R.id.sciName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lib, parent, false)
        return LibraryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val library = libraryList[position]
        holder.libraryImage.setImageResource(library.imageResId)
        holder.libraryTitle.text = library.name
        holder.sciName.text = library.sciName
    }

    override fun getItemCount(): Int = libraryList.size
}
