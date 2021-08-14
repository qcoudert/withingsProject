package com.qcoudert.withingsproject.imageDisplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.databinding.ImageDisplayerActivityBinding
import com.qcoudert.withingsproject.home.MainFragment

class ImageDisplayerActivity: AppCompatActivity() {

    companion object {
        const val ARRAY_OF_URLS = "URL_ARRAY"
    }

    private lateinit var binding: ImageDisplayerActivityBinding
    private lateinit var imageURLS: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageURLS = if (intent?.getStringArrayListExtra(ARRAY_OF_URLS)?.isNotEmpty() == true) {
            intent?.getStringArrayListExtra(ARRAY_OF_URLS)!!
        } else {
            listOf()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.image_displayer_activity)
    }


}