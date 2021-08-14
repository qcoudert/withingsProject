package com.qcoudert.withingsproject.imageDisplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.home.MainFragment

class ImageDisplayerActivity: AppCompatActivity() {

    companion object {
        const val ARRAY_OF_URLS = "URL_ARRAY"
    }

    private lateinit var imageURLS: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageURLS = if (intent?.getStringArrayExtra(ARRAY_OF_URLS)?.isNotEmpty() == true) {
            intent?.getStringArrayExtra(ARRAY_OF_URLS)!!.toList()
        } else {
            listOf()
        }
        setContentView(R.layout.main_activity)
    }


}