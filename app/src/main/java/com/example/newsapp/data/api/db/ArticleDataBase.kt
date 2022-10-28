package com.example.newsapp.data.api.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDataBase? = null
        private var Lock = Any()
    }

    operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
        instance ?: createDataBase(context).also { instance = it }
    }

    private fun createDataBase(context: Context): ArticleDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDataBase::class.java,
            "article_database"
        ).build()
    }
}