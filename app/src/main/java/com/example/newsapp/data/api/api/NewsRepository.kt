package com.example.newsapp.data.api.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getHeadLines(countryCode = countryCode, page = pageNumber)
}