package com.example.newsapp.data.api.api

import javax.inject.Inject

class TestRepo @Inject constructor(private val newsService: NewsService) {
    suspend fun getAll() = newsService.getHeadLines()
}