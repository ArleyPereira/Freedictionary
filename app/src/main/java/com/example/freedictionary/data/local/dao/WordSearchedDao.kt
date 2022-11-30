package com.example.freedictionary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.freedictionary.data.local.entity.*

@Dao
interface WordSearchedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWordSearched(wordsSearchedEntity: WordsSearchedEntity): Long

    @Query("SELECT * FROM WORDS_SEARCHED_TABLE WHERE word = :word")
    suspend fun getWordSearched(word: String): WordsSearchedEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhonetic(phoneticEntity: PhoneticEntity): Long

    @Query("SELECT * FROM PHONETIC_TABLE WHERE word_searched_id = :wordSearchedId")
    suspend fun getPhonetics(wordSearchedId: Long): List<PhoneticEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeaning(meaningEntity: MeaningEntity): Long

    @Query("SELECT * FROM MEANING_TABLE WHERE word_searched_id = :wordSearchedId")
    suspend fun getMeanings(wordSearchedId: Long): List<MeaningEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDefinition(definitionEntity: DefinitionEntity): Long

    @Query("SELECT * FROM DEFINITION_TABLE WHERE meaning_id = :meaningId")
    suspend fun getDefinitions(meaningId: Long): List<DefinitionEntity>

}