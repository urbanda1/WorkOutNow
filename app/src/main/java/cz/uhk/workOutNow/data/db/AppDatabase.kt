package cz.uhk.workOutNow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.uhk.workOutNow.data.db.dao.TrainingEntityDao
import cz.uhk.workOutNow.data.db.entities.SettingsEntity
import cz.uhk.workOutNow.data.db.entities.TrainingEntity
import cz.uhk.workOutNow.data.db.entities.TrainingListEntity

@Database(
    entities = [TrainingEntity::class, TrainingListEntity::class, SettingsEntity::class],
    version = AppDatabase.Version
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainingEntityDao(): TrainingEntityDao

    companion object {

        const val Version = 1
        const val Name = "WorkOutNowDb"

    }

}