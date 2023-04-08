package cz.uhk.workOutNow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.uhk.workOutNow.data.db.dao.NoteDao
import cz.uhk.workOutNow.data.db.entities.NoteEntity

@Database(
    entities = [ NoteEntity::class ],
    version = AppDatabase.Version
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        const val Version = 1
        const val Name = "WorkOutNowDb"

    }

}