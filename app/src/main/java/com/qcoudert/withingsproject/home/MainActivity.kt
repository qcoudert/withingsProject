package com.qcoudert.withingsproject.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qcoudert.withingsproject.R
import com.qcoudert.withingsproject.displayResult.DisplayResultFragment
import com.qcoudert.withingsproject.pixabay.PixabayHit

class MainActivity : AppCompatActivity() {

    private var isShowingListOfImage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
            isShowingListOfImage = false
        }
    }

    fun switchToDisplayResult(pixabayHits: List<PixabayHit>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DisplayResultFragment.newInstance(pixabayHits)).commitNow()
        isShowingListOfImage = true
    }

    fun switchToHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
        isShowingListOfImage = false
    }

    override fun onBackPressed() {
        if(isShowingListOfImage) {
            switchToHome()
        } else {
            super.onBackPressed()
        }
    }
}