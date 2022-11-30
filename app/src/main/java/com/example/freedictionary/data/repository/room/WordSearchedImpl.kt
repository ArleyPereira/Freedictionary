package com.example.freedictionary.data.repository.room

import com.example.freedictionary.data.local.dao.WordSearchedDao
import com.example.freedictionary.data.local.entity.DefinitionEntity
import com.example.freedictionary.data.local.entity.MeaningEntity
import com.example.freedictionary.data.local.entity.PhoneticEntity
import com.example.freedictionary.data.local.entity.WordsSearchedEntity
import com.example.freedictionary.domain.repository.room.WordSearched
import javax.inject.Inject

class WordSearchedImpl @Inject constructor(
    private val wordSearchedDao: WordSearchedDao
): WordSearched {

    override suspend fun insertWordSearched(wordsSearchedEntity: WordsSearchedEntity): Long {
        return wordSearchedDao.insertWordSearched(wordsSearchedEntity)
    }

    override suspend fun getWordSearched(word: String): WordsSearchedEntity? {
        return wordSearchedDao.getWordSearched(word)
    }

    override suspend fun insertPhonetic(phoneticEntity: PhoneticEntity): Long {
        return wordSearchedDao.insertPhonetic(phoneticEntity)
    }

    override suspend fun getPhonetics(wordsSearchedId: Long): List<PhoneticEntity> {
        return wordSearchedDao.getPhonetics(wordsSearchedId)
    }

    override suspend fun insertMeaning(meaningEntity: MeaningEntity): Long {
        return wordSearchedDao.insertMeaning(meaningEntity)
    }

    override suspend fun getMeanings(wordsSearchedId: Long): List<MeaningEntity> {
        return wordSearchedDao.getMeanings(wordsSearchedId)
    }

    override suspend fun insertDefinition(definitionEntity: DefinitionEntity): Long {
        return wordSearchedDao.insertDefinition(definitionEntity)
    }

    override suspend fun getDefinitions(meaningId: Long): List<DefinitionEntity> {
        return wordSearchedDao.getDefinitions(meaningId)
    }
}