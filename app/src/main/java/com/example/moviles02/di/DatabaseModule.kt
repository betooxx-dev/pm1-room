package com.example.moviles02.di

import android.content.Context
import com.example.moviles02.dao.StudentDao
import com.example.moviles02.database.AppDatabase
import com.example.moviles02.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideStudentDao(database: AppDatabase): StudentDao {
        return database.studentDao()
    }

    @Provides
    @Singleton
    fun provideStudentRepository(studentDao: StudentDao): StudentRepository {
        return StudentRepository(studentDao)
    }
}