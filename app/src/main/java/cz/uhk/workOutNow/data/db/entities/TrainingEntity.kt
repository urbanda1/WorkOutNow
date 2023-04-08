package cz.uhk.workOutNow.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Required

@Entity
data class TrainingEntity(

    @PrimaryKey(autoGenerate = true)
    val trainingEntityId: Long = 0,

    //foreign key on TrainingListEntity
    val trainingListEntityId: Long,

    @Required
    val name: String,

    @Required
    val duration: Long,

    @Required // uživatel bude vybírat text z nějakého omezeného seznamu a podle textu se zobrazí ikona
    val icon: String

)
