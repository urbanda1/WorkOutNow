package cz.uhk.workOutNow.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// vice info viz dokumentace knihovny room
@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String,
    val priority: Int = 1,
    val solved: Boolean = false
)
