package com.qcoudert.withingsproject.displayResult

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.pixabay.PixabayHit
import com.squareup.picasso.Picasso

class PixabayHitsAdapter(private val pixabayHits: List<PixabayHit>) :
    RecyclerView.Adapter<PixabayHitsAdapter.PixabayHitViewHolder>() {

    val selectedItems = HashMap<Int, PixabayHit>()
    val isItemSelected = MutableLiveData(false)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayHitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_preview_item, parent, false)
        return PixabayHitViewHolder(view)
    }

    override fun onBindViewHolder(holder: PixabayHitViewHolder, position: Int) {
        holder.bind(pixabayHits[position])
        holder.itemView.setOnClickListener {
            holder.isSelected = !holder.isSelected
            if(holder.isSelected) {
                it.findViewById<ImageView>(R.id.pixabayPreviewImageView).alpha = 0.5F
                selectedItems[position] = pixabayHits[position]
                isItemSelected.value = true
            } else {
                it.findViewById<ImageView>(R.id.pixabayPreviewImageView).alpha = 1F
                if (selectedItems.containsKey(position)) {
                    selectedItems.remove(position)
                    if(selectedItems.isEmpty()) {
                        isItemSelected.value = false
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return pixabayHits.size
    }

    class PixabayHitViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var pixabayHit: PixabayHit? = null
        var isSelected = false

        fun bind(pixabayHit: PixabayHit) {
            this.pixabayHit = pixabayHit
            Picasso.get().load(pixabayHit.previewURL).into(itemView.findViewById<ImageView>(R.id.pixabayPreviewImageView))
        }
    }
}