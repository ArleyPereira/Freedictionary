package com.example.freedictionary.di

import com.example.freedictionary.data.repository.firebase.FirebaseRepositoryImpl
import com.example.freedictionary.data.repository.remote.WordApiDataSourceImpl
import com.example.freedictionary.data.repository.room.WordDbDataSourceImpl
import com.example.freedictionary.data.repository.room.WordRepositoryImpl
import com.example.freedictionary.data.repository.room.WordSearchedImpl
import com.example.freedictionary.domain.repository.firebase.FirebaseRepository
import com.example.freedictionary.domain.repository.remote.WordApiDataSource
import com.example.freedictionary.domain.repository.room.WordDbDataSource
import com.example.freedictionary.domain.repository.room.WordRepository
import com.example.freedictionary.domain.repository.room.WordSearched
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsFirebaseRepository(
        firebaseRepositoryImpl: FirebaseRepositoryImpl
    ): FirebaseRepository

    @Binds
    fun bindsWordSearched(
        wordSearchedImpl: WordSearchedImpl
    ): WordSearched

    @Binds
    fun bindsWordDbDataSource(
        wordDbDataSourceImpl: WordDbDataSourceImpl
    ): WordDbDataSource

    @Binds
    fun bindsWordApiDataSource(
        wordApiDataSourceImpl: WordApiDataSourceImpl
    ): WordApiDataSource

    @Binds
    fun bindWordRepository(
        repository: WordRepositoryImpl
    ): WordRepository

}