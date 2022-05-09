package com.suatzengin.quizapp.di

import android.content.Context
import androidx.room.Room
import com.suatzengin.quizapp.data.local.QuizDao
import com.suatzengin.quizapp.data.local.QuizDatabase
import com.suatzengin.quizapp.data.local.UserDao
import com.suatzengin.quizapp.data.repository.QuizRepositoryImpl
import com.suatzengin.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): QuizDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            QuizDatabase::class.java,
            "quiz_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: QuizDatabase): UserDao{
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideQuizDao(db: QuizDatabase): QuizDao{
        return db.quizDao()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(userDao: UserDao, quizDao: QuizDao): QuizRepository{
        return QuizRepositoryImpl(userDao, quizDao)
    }
}