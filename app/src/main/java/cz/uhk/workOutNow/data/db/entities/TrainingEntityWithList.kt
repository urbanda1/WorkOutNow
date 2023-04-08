package cz.uhk.workOutNow.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingEntityWithList(
    @Embedded val trainingEntity: TrainingEntity,
    @Relation(
        parentColumn = "trainingListEntityId",
        entityColumn = "trainingListEntityId"
    )
    val trainingListEntity: TrainingListEntity
)