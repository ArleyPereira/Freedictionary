package com.example.freedictionary.domain.repository.room

import com.example.freedictionary.data.local.entity.DefinitionEntity
import com.example.freedictionary.data.local.entity.MeaningEntity
import com.example.freedictionary.data.local.entity.PhoneticEntity
import com.example.freedictionary.data.local.entity.WordsSearchedEntity

interface WordSearched {

    suspend fun insertWordSearched(wordsSearchedEntity: WordsSearchedEntity): Long

    suspend fun getWordSearched(word: String): WordsSearchedEntity?

    suspend fun insertPhonetic(phoneticEntity: PhoneticEntity): Long

    suspend fun getPhonetics(wordsSearchedId: Long): List<PhoneticEntity>

    suspend fun insertMeaning(meaningEntity: MeaningEntity): Long

    suspend fun getMeanings(wordsSearchedId: Long): List<MeaningEntity>

    suspend fun insertDefinition(definitionEntity: DefinitionEntity): Long

    suspend fun getDefinitions(meaningId: Long): List<DefinitionEntity>

}