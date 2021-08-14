package com.qcoudert.withingsproject.imageDisplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.databinding.ImageDisplayerActivityBinding
import com.squareup.picasso.Picasso

class ImageDisplayerActivity: AppCompatActivity() {

    companion object {
        const val ARRAY_OF_URLS = "URL_ARRAY"
    }

    private lateinit var binding: ImageDisplayerActivityBinding
    private lateinit var imageURLS: List<String>
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageURLS = if (intent?.getStringArrayListExtra(ARRAY_OF_URLS)?.isNotEmpty() == true) {
            intent?.getStringArrayListExtra(ARRAY_OF_URLS)!!
        } else {
            listOf()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.image_displayer_activity)
        binding.displayImageView.alpha = 0F
        fadeIn()
    }

    fun fadeIn() {
        Picasso.get().load(imageURLS[position]).into(binding.displayImageView)
        if (position+1 < imageURLS.size) {
            position++
        } else {
            position = 0
        }
        binding.displayImageView.animate().setDuration(2000).alpha(1F).withEndAction(::fadeOut)
    }

    fun fadeOut() {
        binding.displayImageView.animate().setDuration(2000).alpha(0F).withEndAction(::fadeIn)
    }


}