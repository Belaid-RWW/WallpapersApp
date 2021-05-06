package com.example.wallpaperapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PhotosAdapter(private val context: Context,
                    private val images: List<PhotoModel>,
                    val listener: (PhotoModel) -> Unit
                    ): RecyclerView.Adapter<PhotosAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageSrc = view.findViewById<ImageView>(R.id.img_test)
        fun bindView(image:PhotoModel, listener:(PhotoModel)-> Unit){
            imageSrc.setImageResource(image.imageA)
            itemView.setOnClickListener{listener(image)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder
        = ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false))


    override fun getItemCount(): Int = images.size



    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position], listener)
    }
}