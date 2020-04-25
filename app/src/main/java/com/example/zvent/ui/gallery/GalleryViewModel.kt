package com.example.zvent.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zvent is dedicated for those who are looking for some fun out there were you don't" +
                "have to worry about any detail. Be prepared to have a new organizing way to control who came to your party or event" +
                "without going crazy. Mark the ✓ symbol for those who came and the ✕ for those who didn't.  " +
                "Enjoy your party!"
    }
    val text: LiveData<String> = _text
}