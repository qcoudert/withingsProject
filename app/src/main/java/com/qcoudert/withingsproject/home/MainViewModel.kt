package com.qcoudert.withingsproject.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val firstImageURL = MutableLiveData("Click the button to send the query !")
    val queryText = MutableLiveData("")
}