package com.example.freedictionary.domain.usecase.room

import com.example.freedictionary.data.local.entity.DefinitionEntity
import com.example.freedictionary.data.local.entity.MeaningEntity
import com.example.freedictionary.data.local.entity.WordsSearchedEntity
import com.example.freedictionary.data.remote.model.Phonetic
import com.example.freedictionary.data.remote.model.toEntity
import com.example.freedictionary.domain.model.WordSearchedDomain
import com.example.freedictionary.domain.repository.room.WordSearched
import javax.inject.Inject

class InsertWordSearchedUseCase @Inject constructor(
    private val repository: WordSearched
) {

    suspend operator fun invoke(wordSearchedDomain: WordSearchedDomain): Long {
        val wordsSearchedEntity = WordsSearchedEntity(
            origin = wordSearchedDomain.origin,
            phonetic = wordSearchedDomain.phonetic,
            word = wordSearchedDomain.word
        )
        val wordSearchedId = repository.insertWordSearched(wordsSearchedEntity)

        wordSearchedDomain.phonetics?.forEach { phonetic ->
            repository.insertPhonetic(
                Phonetic(
                    wordSearchedId = wordSearchedId,
                    audio = phonetic.audio,
                    text = phonetic.text
                ).toEntity()
            )
        }

        wordSearchedDomain.meanings?.forEach { meaning ->
            val meaningId = repository.insertMeaning(
                MeaningEntity(
                    wordSearchedId = wordSearchedId,
                    partOfSpeech = meaning.partOfSpeech
                )
            )

            meaning.definitions?.forEach { definition ->
                repository.insertDefinition(
                    DefinitionEntity(
                        meaningId = meaningId,
                        definition = definition.definition,
                        example = definition.example
                    )
                )
            }
        }

        return wordSearchedId
    }

}