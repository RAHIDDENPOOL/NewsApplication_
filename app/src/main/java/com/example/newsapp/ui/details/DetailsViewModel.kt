package com.example.newsapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.api.api.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: NewsRepository): ViewModel(){
    init {

    }
}