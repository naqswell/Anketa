package com.example.anketa.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anketa.databinding.ItemViewpagerBinding

class ViewPager2Adapter(private var images: Array<Int>) :
    RecyclerView.Adapter<ViewPager2Adapter.ImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val itemBinding =
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        parent.overScrollMode = View.OVER_SCROLL_NEVER
        return ImageHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateImages(newImages: Array<Int>) {
        this.images = newImages
        notifyDataSetChanged()
    }

    inner class ImageHolder(private val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.images.setImageResource(image)
        }
    }
}