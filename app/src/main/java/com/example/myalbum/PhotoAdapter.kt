package com.example.myalbum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbum.model.Photo


class PhotoAdapter(photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val mPhotos: List<Photo> = photos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo: Photo = mPhotos[position]
        holder.mImageView.setImageBitmap(photo.getThumbnail())
        holder.mTextView.text = photo.name
    }

    override fun getItemCount(): Int {
        return mPhotos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView = itemView.findViewById(R.id.image_view)
        var mTextView: TextView = itemView.findViewById(R.id.text_view)

    }

}
