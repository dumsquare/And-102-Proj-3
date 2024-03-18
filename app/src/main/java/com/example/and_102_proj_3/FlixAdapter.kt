package com.example.and_102_proj_3

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FlixAdapter(private val flixList:List<String>, private val titleList: List<String>, private val descriptionList: List<String>) : RecyclerView.Adapter<FlixAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleTextView: TextView
        val descriptionTextView: TextView
        val flixImageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.titleView)
            descriptionTextView = itemView.findViewById(R.id.descriptionView)
            flixImageView = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context= parent.context
        val inflater = LayoutInflater.from(context)
        val flixView = inflater.inflate(R.layout.flix_item, parent, false)
        return ViewHolder(flixView)

    }

    override fun getItemCount(): Int {
        return flixList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(flixList[position])
            .centerCrop()
            .into(holder.flixImageView)

        holder.flixImageView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Flix at position $position clicked", Toast.LENGTH_SHORT).show()
        }
        holder.titleTextView.text = titleList[position]
        holder.descriptionTextView.text = descriptionList[position]
    }
}