package cz.uhk.workOutNow.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Required

@Entity
data class TrainingListEntity(

    @PrimaryKey(autoGenerate = true)
    val trainingListEntityId: Long = 0,

    @Required
    val title: String,

    @Required
    val description: String,

    @Required // uživatel bude vybírat text z nějakého omezeného seznamu a podle textu se zobrazí ikona
    val icon: String

)
