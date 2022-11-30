package com.example.freedictionary.domain.usecase.room

import com.example.freedictionary.data.local.entity.DefinitionEntity
import com.example.freedictionary.data.local.entity.MeaningEntity
import com.example.freedictionary.data.local.entity.PhoneticEntity
import com.example.freedictionary.data.local.entity.toDomain
import com.example.freedictionary.domain.model.MeaningDomain
import com.example.freedictionary.domain.model.WordSearchedDomain
import com.example.freedictionary.domain.repository.room.WordSearched
import javax.inject.Inject

class GetWordSearchedUseCase @Inject constructor(
    private val repository: WordSearched
) {
    suspend operator fun invoke(word: String): WordSearchedDomain? {
        val wordSearched = repository.getWordSearched(word)

        val definitionList: MutableList<DefinitionEntity> = mutableListOf()
        val phoneticList: MutableList<PhoneticEntity> = mutableListOf()
        val meaningList: MutableList<MeaningEntity> = mutableListOf()

        if (wordSearched?.id != null) {
            phoneticList.addAll(repository.getPhonetics(wordSearched.id))
            meaningList.addAll(repository.getMeanings(wordSearched.id))

            meaningList.forEach {
                val definitions = repository.getDefinitions(it.id)
                definitionList.addAll(definitions)
            }
        }

        val wordSearchedDomain = WordSearchedDomain(
            id = wordSearched?.id,
            word = wordSearched?.word,
            phonetic = wordSearched?.phonetic,
            phonetics = phoneticList.map { it.toDomain() },
            origin = wordSearched?.origin,
            meanings = meaningList.map { meaningEntity ->
                val definitions = definitionList.filter {
                    it.meaningId == meaningEntity.id
                }.map {
                    it.toDomain()
                }

                MeaningDomain(
                    wordSearchedId = meaningEntity.wordSearchedId,
                    definitions = definitions,
                    partOfSpeech = meaningEntity.partOfSpeech
                )
            }
        )

        return if (wordSearched != null) {
            wordSearchedDomain
        } else {
            null
        }
    }

}