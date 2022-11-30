package com.example.freedictionary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freedictionary.data.remote.model.Definition
import com.example.freedictionary.util.Constants.Database.DEFINATION_TABLE

@Entity(tableName = DEFINATION_TABLE)
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "meaning_id")
    val meaningId: Long,

    val definition: String?,
    val example: String?
)

fun DefinitionEntity.toDomain(): Definition {
    return with(this) {
        Definition(
            definition = this.definition,
            example = this.example
        )
    }
}